/****************************************************
* File: engine.h									*
* Authors: James Daly, Michael Cline,				*
*	Rusty Zufall, and Christopher Feragotti			*
* Emails: jcd5044@psu.edu, mrc5163@psu.edu,			*
*	drz5001@psu.edu, and cwf5024@psu.edu			*
* Description: contains class declarations for		*
*	Attributes, Conditions, and Rules				*
****************************************************/

/****************************************************
* File: engine.h									*
* Authors: James Daly, Michael Cline,				*
*	Rusty Zufall, and Christopher Feragotti			*
* Emails: jcd5044@psu.edu, mrc5163@psu.edu,			*
*	drz5001@psu.edu, and cwf5024@psu.edu			*
* Description: contains class declarations			*
*	for Attributes, Conditions, and Rules			*
****************************************************/

#include <stdio.h>
#include <fstream>
#include <iostream>
#include <vector>
#include <string>
#include <cctype>
#include <stdlib.h>
#include <sstream>
#include "math.h"

#define JCD5044_MAX_SIZE 256
#define JCD5044_AND 558
#define JCD5044_OR 559

using namespace std;

class JCD5044_Rule
{
public:
	JCD5044_Rule(string ante, string cons)
	{
		antecedent = ante;
		consequent = cons;
		operation = JCD5044_AND;	// AND will be default
		counter = 0;		// counter for how many more conditions must be satisfied
		totalTrue = 0;		// so we know how many of its conditions are true
	}

	void ChangeOp(int andor)
	{
		operation = andor;
	}

	void IncrementCounter()
	{
		// make sure we don't increment the counter past 1 for rules with ORs
		if ( operation == JCD5044_AND || counter == 0 )
			counter++;
	}

	// a condition in the OR has become false 
	void AttemptToIncrement()
	{
		if ( totalTrue > 1 )
			totalTrue--;
		else
		{
			totalTrue = 0;
			counter = 1;
		}
	}

	void DecrementCounter()
	{
		totalTrue++;
		if ( counter > 0 )
			counter--;
	}

	void SetCounter(int input)
	{
		counter = input;
	}

	int GetCounter()
	{
		return counter;
	}

	int GetOperation()
	{
		return operation;
	}

	string GetAntecedent()
	{
		return antecedent;
	}

	string GetConsequent()
	{
		return consequent;
	}

private:
	int counter, totalTrue;	// counter to keep track of the number of unsatisfied conditions
	string antecedent, consequent;
	int operation;	// AND or OR, used to know how many of the conditions must be satisfied (either 1 or all)
};

class JCD5044_Condition
{
public:
	JCD5044_Condition(string input, JCD5044_Rule* rule)
	{
		content = input;
		truth = false;
		rules.push_back(rule);
	}

	void AddRule(JCD5044_Rule* rule)
	{
		// make sure we don't add duplicate rules
		for ( unsigned int i = 0; i < rules.size(); i++ )
		{
			if ( (*rule).GetAntecedent().compare((*rules[i]).GetAntecedent()) == 0 && (*rule).GetConsequent().compare((*rules[i]).GetConsequent()) == 0 )
				return;
		}
		rules.push_back(rule);
	}

	string GetContent()
	{
		return content;
	}

	// loop through each condition's rule list and increment their counters
	void CountConditionsForRules()
	{
		for ( unsigned int i = 0; i < rules.size(); i++ )
			(*rules[i]).IncrementCounter();
	}

	bool GetTruth()	{		return truth;	}

	void ToggleTruth()	{		truth = !truth;	}

	void SetTruth(bool temp)	{		truth = temp;	}

	void SignalRules()
	{
		for ( unsigned int i = 0; i < rules.size(); i++ )
		{
			if (truth)
					(*rules[i]).DecrementCounter();
			else
			{
				if ( (*rules[i]).GetOperation() == JCD5044_AND )
					(*rules[i]).IncrementCounter();
				else
				{
					// this is a tricky situation, we don't want to increment the counter if another condition in the OR is true...
					(*rules[i]).AttemptToIncrement();
				}
			}
		}
	}

private:
	string content;
	vector<JCD5044_Rule*> rules;
	bool truth;
};

class JCD5044_Attribute
{
public:
	JCD5044_Attribute(string input)
	{
		identifier = input;
		value = -123456789;
	}

	JCD5044_Attribute(string input, int val)
	{
		identifier = input;
		value = val;
	}

	JCD5044_Attribute(string input, JCD5044_Condition* condition)
	{
		identifier = input;
		value = -123456789;
		conditions.push_back(condition);
	}

	JCD5044_Attribute(string input, JCD5044_Condition* condition, int val)
	{
		identifier = input;
		value = val;
		conditions.push_back(condition);
	}

	string GetContent()
	{
		return identifier;
	}

	int GetValue()
	{
		return value;
	}

	void SetValue(int val)
	{
		value = val;
	}

	vector<JCD5044_Condition*> GetConditionList()
	{
		return conditions;
	}

	// make sure we don't add duplicate conditions
	void AddCondition(JCD5044_Condition* condition)
	{
		for ( unsigned int i = 0; i < conditions.size(); i++ )
		{
			if ( (*condition).GetContent().compare((*conditions[i]).GetContent()) == 0 )
				return;
		}
		conditions.push_back(condition);
	}

private:
	string identifier;
	vector<JCD5044_Condition*> conditions;
	int value;
};

class JCD5044_Engine
{
public:
	JCD5044_Engine()
	{
		char input[JCD5044_MAX_SIZE];
		ifstream infile;
		string antecedent, consequent, content, buffer, buffer2;
		int index, index2, start, j, m;
		unsigned int k;
		JCD5044_Rule *newRule;
		JCD5044_Condition *newCond;
		JCD5044_Attribute *newAttr;

		// read from file
		infile.open("files/jcd5044.dat1");
		if ( infile.fail() )
		{
			cout << "Could not open files/jcd5044.dat1!" << endl;
			exit(0);
		}
		while ( !infile.eof() && infile )
		{
			infile.getline(input, JCD5044_MAX_SIZE);
			content = string(input);
			index = (int)content.find("THEN");
			if ( index == -1 || (content.substr(0, 2)).compare("//") == 0 )
				continue;
			// split the rule into antecedent and consequent
			antecedent = content.substr(3, index-4);
			consequent = content.substr(index+5, content.length());
			// push rule onto end of rules vector
			newRule = new JCD5044_Rule(antecedent, consequent);
			rules.push_back(newRule);
			
			// HANDLE ANTECEDENTS THAT ARE SEPARATED BY 'AND's
			index = (int)antecedent.find("AND");
			if ( index > -1 )
			{
				start = 0;
				while ( index != -1 )
				{
					buffer = antecedent.substr(start, index-start-1);
					// search for condition so we don't duplicate entries
					newCond = FindCondition(trimString(buffer));
					if ( newCond == NULL )
					{
						// we have a new entry, so add this rule to a new condition
						newCond = new JCD5044_Condition(buffer, newRule);
						// add condition to conditions vector
						conditions.push_back(newCond);
					}
					else
						(*newCond).AddRule(newRule);

					j = -1;
					unsigned int b = 0;
					// break down each condition into attributes
					while ( b < buffer.length() )
					{
						if ( isalpha(buffer[b]) )
						{
							if ( j == -1 )
								j = b;
						}
						if ( j > -1 && ( (!isalpha(buffer[b]) && !isdigit(buffer[b])) || buffer[b] == ' ' || b+1 == buffer.length() ) )
						{
							// if we are dealing with a BOARD() call, check its arguments for attributes
							if ( (buffer.substr(0,6)).compare("BOARD(") == 0 || (buffer.substr(0,5)).compare("AREA(") == 0 )
							{
								index2 = (int)buffer.find(")");
								if ( buffer.substr(0,6).compare("BOARD(") == 0 )
									buffer2 = buffer.substr(6, index2-6);
								else
									buffer2 = buffer.substr(5, index2-5);
								k = 0;
								m = -1;
								while ( k < buffer2.length() )
								{
									if ( isalpha (buffer2[k]) && m == -1 )
										m = k;
									
									if ( m > -1 && ( (!isalpha(buffer2[k]) && !isdigit(buffer2[k])) || buffer2[k] == ' ' || buffer2[k] == '+' || buffer2[k] == '-' || buffer2[k] == '*' || buffer2[k] == '/' || buffer2[k] == ',' || k+1 == buffer2.length() ) )
									{
										if ( k+1 == buffer2.length() )
											k++;
										newAttr = FindAttribute(trimString(buffer2.substr(m, k-m)));
										if ( newAttr == NULL )
										{
											newAttr = new JCD5044_Attribute(trimString(buffer2.substr(m, k-m)), newCond);
											attributes.push_back(newAttr);
										}
										else
											(*newAttr).AddCondition(newCond);
										m = -1;
									}
									k++;
								}
							}
							else
							{
								if ( b+1 == buffer.length() )
									b++;
								newAttr = FindAttribute(trimString(buffer.substr(j, b-j)));
								if ( newAttr == NULL )
								{
									newAttr = new JCD5044_Attribute(trimString(buffer.substr(j, b-j)), newCond);
									attributes.push_back(newAttr);
								}
								else
									(*newAttr).AddCondition(newCond);
							}
							j = -1;
						}
						b++;
					}
					start = index+4;
					index = (int)antecedent.find("AND", start);				
				}
				buffer = antecedent.substr(start, antecedent.length()-start);
				newCond = FindCondition(trimString(buffer));
				if ( newCond == NULL )
				{
					newCond = new JCD5044_Condition(trimString(buffer), newRule);
					conditions.push_back(newCond);
				}
				else
					(*newCond).AddRule(newRule);

				j = -1;
				unsigned int b = 0;
				// break down each condition into attributes
				while ( b < buffer.length() )
				{
					if ( isalpha(buffer[b]) )
					{
						if ( j == -1 )
							j = b;
					}
					if ( j > -1 && ( (!isalpha(buffer[b]) && !isdigit(buffer[b])) || buffer[b] == ' ' || b+1 == buffer.length() ) )
					{
						if ( (buffer.substr(0,6)).compare("BOARD(") == 0 || (buffer.substr(0,5)).compare("AREA(") == 0 )
						{
							index2 = (int)buffer.find(")");
							if ( buffer.substr(0,6).compare("BOARD(") == 0 )
								buffer2 = buffer.substr(6, index2-6);
							else
								buffer2 = buffer.substr(5, index2-5);
							k = 0;
							m = -1;
							while ( k < buffer2.length() )
							{
								if ( isalpha (buffer2[k]) && m == -1 )
									m = k;
								
								if ( m > -1 && ( (!isalpha(buffer2[k]) && !isdigit(buffer2[k])) || buffer2[k] == ' ' || buffer2[k] == '+' || buffer2[k] == '-' || buffer2[k] == '*' || buffer2[k] == '/' || buffer2[k] == ',' || k+1 == buffer2.length() ) )
								{
									if ( k+1 == buffer2.length() )
										k++;
									newAttr = FindAttribute(trimString(buffer2.substr(m, k-m)));
									if ( newAttr == NULL )
									{
										newAttr = new JCD5044_Attribute(trimString(buffer2.substr(m, k-m)), newCond);
										attributes.push_back(newAttr);
									}
									else
										(*newAttr).AddCondition(newCond);
									m = -1;
								}
								k++;
							}
						}
						else
						{
							if ( b+1 == buffer.length() )
								b++;
							newAttr = FindAttribute(trimString(buffer.substr(j, b-j)));
							if ( newAttr == NULL )
							{
								newAttr = new JCD5044_Attribute(trimString(buffer.substr(j, b-j)), newCond);
								attributes.push_back(newAttr);
							}
							else
								(*newAttr).AddCondition(newCond);
						}
						j = -1;
					}
					b++;
				}
			}

			// HANDLE ANTECEDENTS THAT ARE SEPARATED BY 'OR's
			index = (int)antecedent.find("OR");
			if ( index > -1 )
			{
				start = 0;
				while ( index != -1 )
				{
					buffer = antecedent.substr(start, index-start-1);
					newCond = FindCondition(buffer);
					if ( newCond == NULL )
					{
						newCond = new JCD5044_Condition(buffer, newRule);
						conditions.push_back(newCond);
					}
					else
						(*newCond).AddRule(newRule);

					j = -1;
					unsigned int b = 0;
					// break down each condition into attributes
					while ( b < buffer.length() )
					{
						if ( isalpha(buffer[b]) )
						{
							if ( j == -1 )
								j = b;
						}
						if ( j > -1 && ( (!isalpha(buffer[b]) && !isdigit(buffer[b])) || buffer[b] == ' ' || b+1 == buffer.length() ) )
						{
							if ( (buffer.substr(0,6)).compare("BOARD(") == 0 || (buffer.substr(0,5)).compare("AREA(") == 0 )
							{
								index2 = (int)buffer.find(")");
								if ( buffer.substr(0,6).compare("BOARD(") == 0 )
									buffer2 = buffer.substr(6, index2-6);
								else
									buffer2 = buffer.substr(5, index2-5);
								k = 0;
								m = -1;
								while ( k < buffer2.length() )
								{
									if ( isalpha (buffer2[k]) && m == -1 )
										m = k;
									
									if ( m > -1 && ( (!isalpha(buffer2[k]) && !isdigit(buffer2[k])) || buffer2[k] == ' ' || buffer2[k] == '+' || buffer2[k] == '-' || buffer2[k] == '*' || buffer2[k] == '/' || buffer2[k] == ',' || k+1 == buffer2.length() ) )
									{
										if ( k+1 == buffer2.length() )
											k++;
										newAttr = FindAttribute(trimString(buffer2.substr(m, k-m)));
										if ( newAttr == NULL )
										{
											newAttr = new JCD5044_Attribute(trimString(buffer2.substr(m, k-m)), newCond);
											attributes.push_back(newAttr);
										}
										else
											(*newAttr).AddCondition(newCond);
										m = -1;
									}
									k++;
								}
							}
							else
							{
								if ( b+1 == buffer.length() )
									b++;
								newAttr = FindAttribute(trimString(buffer.substr(j, b-j)));
								if ( newAttr == NULL )
								{
									newAttr = new JCD5044_Attribute(trimString(buffer.substr(j, b-j)), newCond);
									attributes.push_back(newAttr);
								}
								else
									(*newAttr).AddCondition(newCond);
							}
							j = -1;
						}
						b++;
					}
					start = index+3;
					index = (int)antecedent.find("OR", start);				
				}
				buffer = antecedent.substr(start, antecedent.length()-start);
				newCond = FindCondition(buffer);
				if ( newCond == NULL )
				{
					newCond = new JCD5044_Condition(buffer, newRule);
					conditions.push_back(newCond);
				}
				else
					(*newCond).AddRule(newRule);

				j = -1;
				unsigned int b = 0;
				// break down each condition into attributes
				while ( b < buffer.length() )
				{
					if ( isalpha(buffer[b]) )
					{
						if ( j == -1 )
							j = b;
					}
					if ( j > -1 && ( (!isalpha(buffer[b]) && !isdigit(buffer[b])) || buffer[b] == ' ' || b+1 == buffer.length() ) )
					{
						if ( (buffer.substr(0,6)).compare("BOARD(") == 0 || (buffer.substr(0,5)).compare("AREA(") == 0 )
						{
							index2 = (int)buffer.find(")");
							if ( buffer.substr(0,6).compare("BOARD(") == 0 )
								buffer2 = buffer.substr(6, index2-6);
							else
								buffer2 = buffer.substr(5, index2-5);
							k = 0;
							m = -1;
							while ( k < buffer2.length() )
							{
								if ( isalpha (buffer2[k]) && m == -1 )
									m = k;
								
								if ( m > -1 && ( (!isalpha(buffer2[k]) && !isdigit(buffer2[k])) || buffer2[k] == ' ' || buffer2[k] == '+' || buffer2[k] == '-' || buffer2[k] == '*' || buffer2[k] == '/' || buffer2[k] == ',' || k+1 == buffer2.length() ) )
								{
									if ( k+1 == buffer2.length() )
										k++;
									newAttr = FindAttribute(trimString(buffer2.substr(m, k-m)));
									if ( newAttr == NULL )
									{
										newAttr = new JCD5044_Attribute(trimString(buffer2.substr(m, k-m)), newCond);
										attributes.push_back(newAttr);
									}
									else
										(*newAttr).AddCondition(newCond);
									m = -1;
								}
								k++;
							}
						}
						else
						{
							if ( b+1 == buffer.length() )
								b++;
							newAttr = FindAttribute(trimString(buffer.substr(j, b-j)));
							if ( newAttr == NULL )
							{
								newAttr = new JCD5044_Attribute(trimString(buffer.substr(j, b-j)), newCond);
								attributes.push_back(newAttr);
							}
							else
								(*newAttr).AddCondition(newCond);
						}
						j = -1;
					}
					b++;
				}
				(*newRule).ChangeOp(JCD5044_OR);
			}
			
			// HANDLE ANTECEDENTS THAT DON'T CONTAIN 'AND' OR 'OR'
			if ( antecedent.find("AND") == -1 && antecedent.find("OR") == -1 )
			{
				newCond = FindCondition(antecedent);
				if ( newCond == NULL )
				{
					newCond = new JCD5044_Condition(antecedent, newRule);
					conditions.push_back(newCond);
					j = -1;
					unsigned int b = 0;
					// break down each condition into attributes
					while ( b < antecedent.length() )
					{
						if ( isalpha(antecedent[b]) )
						{
							if ( j == -1 )
								j = b;
						}
						if ( j > -1 && ( (!isalpha(antecedent[b]) && !isdigit(antecedent[b])) || antecedent[b] == ' ' || b+1 == buffer.length() ) )
						{
							if ( (antecedent.substr(0,6)).compare("BOARD(") == 0 || (antecedent.substr(0,5)).compare("AREA(") == 0 )
							{
								index2 = (int)antecedent.find(")");
								if ( antecedent.substr(0,6).compare("BOARD(") == 0 )
									buffer2 = antecedent.substr(6, index2-6);
								else
									buffer2 = antecedent.substr(5, index2-5);
								k = 0;
								m = -1;
								while ( k < buffer2.length() )
								{
									if ( isalpha (buffer2[k]) && m == -1 )
										m = k;
									
									if ( m > -1 && ( (!isalpha(buffer2[k]) && !isdigit(buffer2[k])) || buffer2[k] == ' ' || buffer2[k] == '+' || buffer2[k] == '-' || buffer2[k] == '*' || buffer2[k] == '/' || buffer2[k] == ',' || k+1 == buffer2.length() ) )
									{
										if ( k+1 == buffer2.length() )
											k++;
										newAttr = FindAttribute(trimString(buffer2.substr(m, k-m)));
										if ( newAttr == NULL )
										{
											newAttr = new JCD5044_Attribute(trimString(buffer2.substr(m, k-m)), newCond);
											attributes.push_back(newAttr);
										}
										else
											(*newAttr).AddCondition(newCond);
										m = -1;
									}
									k++;
								}
							}
							else
							{
								if ( b+1 == buffer.length() )
									b++;
								newAttr = FindAttribute(trimString(antecedent.substr(j, b-j)));
								if ( newAttr == NULL )
								{
									newAttr = new JCD5044_Attribute(trimString(antecedent.substr(j, b-j)), newCond);
									attributes.push_back(newAttr);
								}
								else
									(*newAttr).AddCondition(newCond);
							}
							j = -1;
						}
						b++;
					}
				}
				else
				{
					(*newCond).AddRule(newRule);
				}
			}
		}
		infile.close();
	}

	// search for a condition in the list, and return a pointer to it if found
	JCD5044_Condition* FindCondition(string input)
	{
		for ( unsigned int i = 0; i < conditions.size(); i++ )
		{
			if ( input.compare((*conditions[i]).GetContent()) == 0 )
				return conditions[i];
		}
		return NULL;
	}

	// search for an attribute in the list, and return a pointer to it if found
	JCD5044_Attribute* FindAttribute(string input)
	{
		for ( unsigned int i = 0; i < attributes.size(); i++ )
		{
			if ( input.compare((*attributes[i]).GetContent()) == 0 )
				return attributes[i];
		}
		return NULL;
	}

	// evaluate the chosen condition's truth
	void EvaluateCondition(JCD5044_Condition *condition)
	{
		string content, equality, term = " ";
		content = (*condition).GetContent();
		int index, leftVal = -123456789, rightVal = -123456789;
		string leftSide, rightSide;
		bool truth;

		// figure out what equality operation we'll be using to check the truth of this condition
		index = (int)content.find("<=");
		if ( index > -1 )
		{
			equality = "<=";
			index++;				// fix index offset due to two-character operator
		}
		else
		{
			index = (int)content.find(">=");
			if ( index > -1 )
			{
				equality = ">=";
				index++;			// fix index offset due to two-character operator
			}
			else
			{
				index = (int)content.find("!=");
				if ( index > -1 )
				{
					equality = "!=";
					index++;		// fix index offset due to two-character operator
				}
				else
				{
					index = (int)content.find("<");
					if ( index > -1 )
						equality = "<";
					else
					{
						index = (int)content.find(">");
						if ( index > -1 )
							equality = ">";
						else
						{
							equality = "=";				// assuming we have well-formed rules, we shouldn't need another default case
							index = (int)content.find("=");
						}
					}
				}
			}
		}

		// break the equation down into left and right sides
		leftSide = content.substr(0, index);
		rightSide = content.substr(index+1, content.length()-index);
		// determine the values of each side
		leftVal = EvaluateExpression(trimString(leftSide));
		rightVal = EvaluateExpression(trimString(rightSide));

		if ( leftVal != -123456789 && rightVal != -123456789 )
		{
			if ( equality.compare("=") == 0 ) { if ( leftVal == rightVal ) truth = true; else truth = false; }
			else if ( equality.compare(">") == 0 ) { if ( leftVal > rightVal ) truth = true; else truth = false; }
			else if ( equality.compare("<") == 0 ) { if ( leftVal < rightVal ) truth = true; else truth = false; }
			else if ( equality.compare(">=") == 0 ) { if ( leftVal >= rightVal ) truth = true; else truth = false; }
			else if ( equality.compare("<=") == 0 ) { if ( leftVal <= rightVal ) truth = true; else truth = false; }
			else if ( equality.compare("!=") == 0 ) { if ( leftVal != rightVal ) truth = true; else truth = false; }
			
			// if the condition's truth has changed, signal the rules that it is contained in
			if ( truth != (*condition).GetTruth() )
			{
				(*condition).ToggleTruth();
				(*condition).SignalRules();
			}
		}
	}

	// an attribute's value has been changed, so evaluate the truth of all conditions that rely on its value
	void FireConditions(JCD5044_Attribute* attr)
	{
		vector<JCD5044_Condition*> conditions;
		conditions = (*attr).GetConditionList();
		for ( unsigned int i = 0; i < conditions.size(); i++ )
			EvaluateCondition( conditions[i] );
	}

	// fire any rules whose conditions have been satisfied
	// return false if we encounter a STOP command in the action list of a rule to cease chaining and make a move
	bool FireRules()
	{
		string actionlist, buffer;
		int index, index2, start, value;
		JCD5044_Attribute *attr;

		// loop through the rules, firing any whose counter has been decremented to zero
		for ( unsigned int i = 0; i < rules.size(); i++ )
		{
			if ( (*rules[i]).GetCounter() == 0 )
			{
				// JCD: this will have to be handled differently in the future
				//		(unless we want to be able to fire a rule more than once in a single turn?)
				(*rules[i]).IncrementCounter();

				actionlist = (*rules[i]).GetConsequent();
				index = (int)actionlist.find(",");
				if ( index == -1 )
				{
					if ( trimString(actionlist).compare("STOP") == 0 )
						return true;

					index2 = (int)actionlist.find("=");
					buffer = actionlist.substr(0, index2);
					attr = FindAttribute(trimString(buffer));
					buffer = actionlist.substr(index2+1, actionlist.length()-index2);
					value = EvaluateExpression(trimString(buffer));
					if ( attr != NULL )
					{
						(*attr).SetValue(value);
						FireConditions(attr);
					}
				}
				else
				{
					start = 0;
					while ( index != -1 )
					{
						buffer = actionlist.substr(start, index-start);
						if ( trimString(buffer).compare("STOP") == 0 )
							return true;

						index2 = (int)buffer.find("=");
						attr = FindAttribute(trimString(buffer.substr(0, index2)));
						value = EvaluateExpression(trimString(buffer.substr(index2, buffer.length()-index2)));
						if ( attr != NULL )
						{
							(*attr).SetValue(value);
							FireConditions(attr);
						}
						start = index+1;
						index = (int)actionlist.find(",", start);
					}
					buffer = actionlist.substr(start, actionlist.length()-start);
					if ( trimString(buffer).compare("STOP") == 0 )
						return true;

					index2 = (int)buffer.find("=");
					attr = FindAttribute(trimString(buffer.substr(0, index2)));
					value = EvaluateExpression(trimString(buffer.substr(index2, buffer.length()-index2)));
					if ( attr != NULL )
					{
						(*attr).SetValue(value);
						FireConditions(attr);
					}
				}
			}
		}
		return false;
	}

	// returns integer value of an expression that includes both attributes and integers
	int EvaluateExpression(string input)
	{
		int value = -123456789, j = -1, k = -1, index;
		string term, buffer;
		char op;

		// support the BOARD(x,y) call
		if ( input.substr(0,6).compare("BOARD(") == 0 )
		{
			string left, right;
			int leftVal, rightVal;
			index = (int)input.find(")");
			buffer = input.substr(6, index-6);
			index = (int)buffer.find(",");
			left = buffer.substr(0, index);
			right = buffer.substr(index+1, buffer.length()-index);
			leftVal = EvaluateExpression(left);
			rightVal = EvaluateExpression(right);
			if ( leftVal == -123456789 || rightVal == -123456789 )
				return -123456789;

			char ret = board[rightVal][leftVal];
			if ( ret == ' ' )
				return 0;
			else if ( ret == '1' )
				return 1;
			else if ( ret == '2' )
				return 2;
			else
				return 5;
		}

		// support the AREA(x,y) call
		if ( input.substr(0,5).compare("AREA(") == 0 )
		{
			string left, right;
			int leftVal, rightVal;
			index = (int)input.find(")");
			buffer = input.substr(5, index-5);
			index = (int)buffer.find(",");
			left = buffer.substr(0, index);
			right = buffer.substr(index+1, buffer.length()-index);
			leftVal = EvaluateExpression(left);
			rightVal = EvaluateExpression(right);
			if ( leftVal == -123456789 || rightVal == -123456789 )
				return -123456789;

			return Area(leftVal,rightVal);
		}

		for ( unsigned int i = 0; i < input.length(); i++ )
		{
			if ( isalpha(input[i]) )
			{
				if ( j == -1 && k == -1 )
					j = i;
			}
			else if ( isdigit(input[i]) )
			{
				if ( k == -1 && j == -1 )
					k = i;
			}
			if ( j > -1 && ((!isalpha(input[i]) && !isdigit(input[i])) || (i == input.length()-1)) )
			{
				if ( i == input.length()-1 )
					i++;
				term = trimString(input.substr(j, i-j));

				JCD5044_Attribute *attr = FindAttribute(term);
				if ( attr != NULL )
				{
					if ( (*attr).GetValue() == -123456789 )
					{
						return -123456789;
					}
					if ( value == -123456789 || term.compare(" ") == 0 )
						value = (*attr).GetValue();
					else
					{
						if ( op == '+' )
							value += (*attr).GetValue();
						else if ( op == '-' )
							value -= (*attr).GetValue();
						else if ( op == '*' )
							value *= (*attr).GetValue();
						else if ( op == '/' )
							value /= (*attr).GetValue();
					}
				}
				j = -1;
			}
			else if ( k > -1 && ((!isalpha(input[i]) && !isdigit(input[i])) || (i == input.length()-1)) )
			{
				if ( i == input.length()-1 )
					i++;
				term = trimString(input.substr(k, i-k));
				if ( value == -123456789 || term.compare(" ") == 0 )
					value = atoi(term.c_str());
				else
				{
					if ( op == '+' )
						value += atoi(term.c_str());
					else if ( op == '-' )
						value -= atoi(term.c_str());
					else if ( op == '*' )
						value *= atoi(term.c_str());
					else if ( op == '/' )
						value /= atoi(term.c_str());
				}

				k = -1;
			}
			if ( input[i] == '+' )
				op = '+';
			if ( input[i] == '-' )
				op = '-';
			if ( input[i] == '*' )
				op = '*';
			if ( input[i] == '/' )
				op = '/';
		}
		return value;
	}

	// trim spaces at the beginning and end of a string
	string trimString(string input)
	{
		string output = input;
		for ( size_t i = 0; i < output.length(); i++ )
		{
			if ( output[i] == ' ' )
			{
				output = output.substr(i+1, output.length()-i);
				i--;
			}
			else
				break;
		}

		for ( size_t i = output.length()-1; i >= 0; i-- )
		{
			if ( output[i] == ' ' || output[i] == '\n' || output[i] == '\r' )
				output = output.substr(0, i);
			else
				break;
		}

		return output;
	}

	double square(double val)
	{
		return val*val;
	}

	int DirDistance()
	{
		return (int)sqrt(square(me_x-opp_x)+square(me_y-opp_y));
	}

	int min4(int a, int b, int c, int d)
	{
		return min(min(a,b),min(c,d));
	}

	int min3(int a, int b, int c)
	{
		return min(a,min(b,c));
	}

	int ShortestPath(int mex, int mey, int oppx, int oppy)
	{
		if( board[mey][mex] == '|' || board[mey][mex] == '-' || board[mey][mex] == '1' || board[mey][mex] == '2' )
		{
			return 1000000;
		}
		if( mex == oppx && (mey - oppy == 1 || mey - oppy == -1) )
		{
			return 1;
		}
		if( mey == oppy && (mex - oppx == 1 || mex - oppx == -1) )
		{
			return 1;
		}

		int i = 0, paths[4] = {-5, -5, -5, -5};
		if ( checklist[mey][mex-1] == 0 )
		{
			i++;
			checklist[mey][mex-1] = 1;
			paths[0] = ShortestPath(mex-1,mey,oppx,oppy);
		}
		if ( checklist[mey][mex+1] == 0 )
		{
			i++;
			checklist[mey][mex+1] = 1;
			if ( paths[0] == -5 )
				paths[0] = ShortestPath(mex+1,mey,oppx,oppy);
			else
				paths[1] = ShortestPath(mex+1,mey,oppx,oppy);
		}
		if ( checklist[mey-1][mex] == 0 )
		{
			i++;
			checklist[mey-1][mex] = 1;
			if ( paths[0] == -5 )
				paths[0] = ShortestPath(mex,mey-1,oppx,oppy);
			else if ( paths[1] == -5 )
				paths[1] = ShortestPath(mex,mey-1,oppx,oppy);
			else
				paths[2] = ShortestPath(mex,mey-1,oppx,oppy);
		}
		if ( checklist[mey+1][mex] == 0 )
		{
			i++;
			checklist[mey+1][mex] = 1;
			if ( paths[0] == -5 )
				paths[0] = ShortestPath(mex,mey+1,oppx,oppy);
			else if ( paths[1] == -5 )
				paths[1] = ShortestPath(mex,mey+1,oppx,oppy);
			else if ( paths[2] == -5 )
				paths[2] = ShortestPath(mex,mey+1,oppx,oppy);
			else
				paths[3] = ShortestPath(mex,mey+1,oppx,oppy);
		}

		if ( i == 0 )
			return 1000000;
		else if ( i == 1 )
			return paths[0];
		else if ( i == 2 )
			return min(paths[0],paths[1]);
		else if ( i == 3 )
			return min3(paths[0],paths[1],paths[2]);
		else
			return min4(paths[0],paths[1],paths[2],paths[3]);
	}

	bool Trapped()
	{
		int path = min4(ShortestPath(me_x-1,me_y,opp_x,opp_y),ShortestPath(me_x+1,me_y,opp_x,opp_y),
			ShortestPath(me_x,me_y-1,opp_x,opp_y),ShortestPath(me_x,me_y+1,opp_x,opp_y));

		if ( path >= 1000000)
			return true;
		else
			return false;
	}

	int AreaPath(int x, int y)
	{
		if ( checklist2[y][x] == 1 )
			return 0;

		checklist2[y][x] = 1;

		return (1 + AreaPath(x+1,y) + AreaPath(x-1,y) + AreaPath(x,y-1) + AreaPath(x,y+1));
	}

	int Area(int x, int y)
	{
		int path = AreaPath(x, y);
		// reset the checklist
		for ( int i = 0; i < MAX_Y; i++ )
		{
			for ( int j = 0; j < MAX_X; j++ )
			{
				if ( board[i][j] == ' ' )
					checklist2[i][j] = 0;
				else
					checklist2[i][j] = 1;
			}
		}
		return path;
	}

	int NumOpponentTopLeft()
	{
		int count = 0;
		char opp = '1';
		if ( player == 1 )
			opp = '2';

		for ( int i = 0; i < MAX_Y/2; i++ )
		{
			for ( int j = 0; j < MAX_X/2; j++ )
			{
				if ( board[i][j] == opp )
					count++;
			}
		}

		return count;
	}

	int NumOpponentTopRight()
	{
		int count = 0;
		char opp = '1';
		if ( player == 1 )
			opp = '2';

		for ( int i = 0; i < MAX_Y/2; i++ )
		{
			for ( int j = MAX_X/2; j < MAX_X; j++ )
			{
				if ( board[i][j] == opp )
					count++;
			}
		}

		return count;
	}

	int NumOpponentBottomLeft()
	{
		int count = 0;
		char opp = '1';
		if ( player == 1 )
			opp = '2';

		for ( int i = MAX_Y/2; i < MAX_Y; i++ )
		{
			for ( int j = 0; j < MAX_X/2; j++ )
			{
				if ( board[i][j] == opp )
					count++;
			}
		}

		return count;
	}

	int NumOpponentBottomRight()
	{
		int count = 0;
		char opp = '1';
		if ( player == 1 )
			opp = '2';

		for ( int i = MAX_Y/2; i < MAX_Y; i++ )
		{
			for ( int j = MAX_X/2; j < MAX_X; j++ )
			{
				if ( board[i][j] == opp )
					count++;
			}
		}

		return count;
	}

	int DirectionOfOpponent()
	{
		if ( me_x == opp_x )
		{
			if ( me_y > opp_y )
				return 0;
			else
				return 4;
		}
		if ( me_y == opp_y )
		{
			if ( me_x > opp_x )
				return 6;
			else
				return 2;
		}
		if ( me_x > opp_x && me_y > opp_y )
			return 7;
		if ( me_x > opp_x && me_y < opp_y )
			return 5;
		if ( me_x < opp_x && me_y > opp_y )
			return 1;
		if ( me_x < opp_x && me_y < opp_y )
			return 3;

		return -1;
	}

	// retrieve the x-coordinate of the move made
	int GetMoveX()
	{
		JCD5044_Attribute *attr = FindAttribute("MOVEx");
		return (*attr).GetValue();
	}

	// retrieve the y-coordinate of the move made
	int GetMoveY()
	{
		JCD5044_Attribute *attr = FindAttribute("MOVEy");
		return (*attr).GetValue();
	}

	// initialize the inference tree
	void Initialize(const char gameboard[MAX_Y][MAX_X], int mex, int mey, int oppx, int oppy)
	{
		string buffer, buf1, buf2;
		int val;
		JCD5044_Attribute *attr, *mexattr, *meyattr, *oppxattr, *oppyattr, *playerattr, *movecountattr, *trappedattr, *dirdistanceattr;
		JCD5044_Attribute *opptopleft, *opptopright, *oppbottomleft, *oppbottomright, *oppdirattr;

		// reset rule counters
		for ( unsigned int i = 0; i < rules.size(); i++ )
			(*rules[i]).SetCounter(0);

		// reset attribute values to default
		for ( unsigned int i = 0; i < attributes.size(); i++ )
			(*attributes[i]).SetValue(-123456789);

		// loop through the conditions and increment counters for each rule
		// also, reset condition truth values to false
		for ( unsigned int i = 0; i < conditions.size(); i++ )
		{
			(*conditions[i]).SetTruth(false);
			// update counters for each rule based on number of conditions that need to be satisfied
			(*conditions[i]).CountConditionsForRules();
		}

		// copy details of the gameboard
		me_x = mex;
		me_y = mey;
		opp_x = oppx;
		opp_y = oppy;
		
		// copy gameboard
		for ( int i = 0; i < MAX_Y; i++ )
		{
			for ( int j = 0; j < MAX_X; j++ )
			{
				board[i][j] = gameboard[i][j];
				checklist[i][j] = 0;
				if ( gameboard[i][j] != ' ' )
					checklist2[i][j] = 1;
				else
					checklist2[i][j] = 0;
			}
		}
		if ( board[me_y][me_x] == '1' )
			player = 1;
		else
			player = 2;

		attr = FindAttribute("MOVEx");
		if ( attr != NULL )
			(*attr).SetValue(me_x);
		else
		{
			attr = new JCD5044_Attribute("MOVEx", me_x);
			attributes.push_back(attr);
		}
		attr = FindAttribute("MOVEy");
		if ( attr != NULL )
			(*attr).SetValue(me_y);
		else
		{
			attr = new JCD5044_Attribute("MOVEy", me_y);
			attributes.push_back(attr);
		}

		// set the values to the most important attributes
		mexattr = FindAttribute("MEx");
		if ( mexattr != NULL )
			(*mexattr).SetValue(me_x);
		meyattr = FindAttribute("MEy");
		if ( meyattr != NULL )
			(*meyattr).SetValue(me_y);
		oppxattr = FindAttribute("OPPx");
		if ( oppxattr != NULL )
			(*oppxattr).SetValue(opp_x);
		oppyattr = FindAttribute("OPPy");
		if ( oppyattr != NULL )
			(*oppyattr).SetValue(opp_y);
		playerattr = FindAttribute("PLAYER");
		if ( playerattr != NULL )
			(*playerattr).SetValue(player);
		int trapped;
		if ( Trapped() )
			trapped = 1;
		else
			trapped = 0;
		trappedattr = FindAttribute("TRAPPED");
		if ( trappedattr != NULL )
			(*trappedattr).SetValue(trapped);
		dirdistanceattr = FindAttribute("OPPDISTANCE");
		if ( dirdistanceattr != NULL )
			(*dirdistanceattr).SetValue(DirDistance());
		opptopleft = FindAttribute("OPPQUAD1");
		if ( opptopleft != NULL )
			(*opptopleft).SetValue(NumOpponentTopLeft());
		opptopright = FindAttribute("OPPQUAD2");
		if ( opptopright != NULL )
			(*opptopright).SetValue(NumOpponentTopRight());
		oppbottomleft = FindAttribute("OPPQUAD3");
		if ( oppbottomleft != NULL )
			(*oppbottomleft).SetValue(NumOpponentBottomLeft());
		oppbottomright = FindAttribute("OPPQUAD4");
		if ( oppbottomright != NULL )
			(*oppbottomright).SetValue(NumOpponentBottomRight());
		oppdirattr = FindAttribute("OPPDIR");
		if ( oppdirattr != NULL )
			(*oppdirattr).SetValue(DirectionOfOpponent());


		int moveCount = 0;
		for ( int i = 0; i < MAX_Y; i++ )
		{
			for ( int j = 0; j < MAX_X; j++ )
			{
				if ( board[i][j] == '1' )
					moveCount++;
			}
		}

		// set CURRENTTURN attribute
		movecountattr = FindAttribute("CURRENTTURN");
		if ( movecountattr != NULL )
			(*movecountattr).SetValue(moveCount);

		// time to set it all off...hope something good happens
		if ( mexattr != NULL ) FireConditions(mexattr);
		if ( meyattr != NULL ) FireConditions(meyattr);
		if ( oppxattr != NULL ) FireConditions(oppxattr);
		if ( oppyattr != NULL ) FireConditions(oppyattr);
		if ( playerattr != NULL ) FireConditions(playerattr);
		if ( movecountattr != NULL ) FireConditions(movecountattr);
		if ( trappedattr != NULL ) FireConditions(trappedattr);
		if ( dirdistanceattr != NULL ) FireConditions(dirdistanceattr);
		if ( opptopleft != NULL ) FireConditions(opptopleft);
		if ( opptopright != NULL ) FireConditions(opptopright);
		if ( oppbottomleft != NULL ) FireConditions(oppbottomleft);
		if ( oppbottomright != NULL ) FireConditions(oppbottomright);
		if ( oppdirattr != NULL ) FireConditions(oppdirattr);
	}

private:
	vector<JCD5044_Attribute*> attributes;
	vector<JCD5044_Condition*> conditions;
	vector<JCD5044_Rule*> rules;
	char board[MAX_Y][MAX_X];
	int checklist[MAX_Y][MAX_X];
	int checklist2[MAX_Y][MAX_X];
	int player, me_x, me_y, opp_x, opp_y;
};

class jcd5044
{
	public:
		jcd5044()
		{
			engine = new JCD5044_Engine();
		}

		void Initialize(_playerData *data)
		{
			printf("I am player %d, starting at %d,%d\n", 
				data->playerNum, data->x, data->y);
		}

		bool Move(const char board[MAX_Y][MAX_X], int& me_x, int& me_y, int them_x, int them_y)
		{
			(*engine).Initialize(board, me_x, me_y, them_x, them_y);
			int i = 0, move_x, move_y;
			bool stop = false;
			// loop and chain
			while ( i < 50 && !stop )
			{
				stop = (*engine).FireRules();
				i++;
			}
			
			// retrieve our move from the attributes set by the rules
			move_x = (*engine).GetMoveX();
			move_y = (*engine).GetMoveY();
			if ( move_x > me_x )
				me_x++;
			else if ( move_x < me_x )
				me_x--;
			else if ( move_y > me_y )
				me_y++;
			else if ( move_y < me_y )
				me_y--;
			else
			{
				if (board[me_y][me_x-1] == ' ')
					me_x--;
				else if (board[me_y-1][me_x] == ' ')
					me_y--;
				else if (board[me_y][me_x+1] == ' ')
					me_x++;
				else if (board[me_y+1][me_x] == ' ')
					me_y++;
				else
					return false;
			}

			return true;
		}
	private:
		JCD5044_Engine *engine;
};
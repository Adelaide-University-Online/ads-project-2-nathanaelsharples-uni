[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/6MtIQWZn)
# ADS Assignment Starter

A basic Java starter project for Algorithms and Data Structures assignments. This project provides a simple foundation that students can build upon for their coursework.

## Project Structure

```
ADSAssignmentStarter/
├── src/
│   └── main/
│       └── java/
│           └── Main.java          # Main entry point with example code
├── .gitignore                     # Git ignore file for Java projects
└── README.md                      # This file
```

**Good luck with your assignments!** 🚀

User Input
When the program starts, the user is prompted to enter the name of the input file and the maximum number of concurrent courses. The input file should contain the degree structure, including course codes and their prerequisite relationships. The program reads this file and construct a directed graph representing the dependency structure of the degree. Three file have been supplied with this assignment, XBDA, XBIT and XBIT_WithFault. These can be used to demonstrate the workings of the code, eg. type XBDA.txt at the prompt. XBIT_WithFault was supplied to show the results of a dependency not existing.
The maximum concurrent courses value specifies the largest number of courses a student is allowed to undertake in a single term. This value is used by the scheduling algorithm to distribute courses across terms while ensuring all prerequisite requirements are satisfied and the term load does not exceed the user-defined limit. By allowing these inputs to be specified at runtime, the program can generate study plans for different degree structures and study-load preferences without requiring any changes to the source code.


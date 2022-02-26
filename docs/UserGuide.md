---
layout: page
title: User Guide
---

# LinkedOUT User Guide v1.2


## LinkedOUT

LinkedOUT is the only application that any experienced recruiter needs. LinkedOUT allows recruiters to keep track of many applicants, and the job they applied for. You can store their contact details, skills and the stage of their application, all in one place.


* Table of Contents
  {:toc}


## Quick Start


1. Ensure you have Java 11 installed on your computer.

2. Download LinkedOUT from {url} (To be updated).
x
3. Copy the jar file into an empty folder

4. Open a command/terminal window in that folder

5. Run the command `java -jar linkedout.jar`

6. Type any command in the command box, and press enter/send


# Features

-   ### Add

Adds a new applicant to be tracked, by adding their name, phone number, the job they have applied for, along with a variable number of skills.

Format:

    add n/NAME p/PHONE_NUMBER j/JOB [s/SKILLS]...

Example:

    add n/Bob p/99999999 j/Data Analyst s/Pandas s/Python s/Java

Sample Output:

    Applicant Bob has been successfully added for job Data Analyst

-  ### View

Allows the user to view an overview of a specific applicant, specified by applicant name

Format:

    view n/NAME

Example:

    view n/Steve Jobs


Sample Output:

    Here’s an overview of Steve Jobs:
    
    Phone Number: 99999999
    
    Job Applied: CEO of Apple
    
    Skills: Product Design  

-   ### List

Show a list of all job applicants, along with an overview of each applicant.

Format:

    list

Example:

    list

Sample Output:

    List of applicants:
    
    1. Steve Jobs
    
    Job Applied: Product Designer
    
    Skills: Product Design
    
    Phone Number: 99999999
    
    2. Elon Musk
    
    Job Applied: Software Developer
    
    Skills: Pandas, Python
    
    Phone Number: 88888888

-   ### Delete

Deletes a specific job applicant, specified by applicant name.

Format:

    delete n/NAME

Example:

    delete n/Elon Musk

Sample Output:

    Applicant Elon Musk has been deleted

-   ### Exit

Exits the application and closes it.

Format:

    exit

Example:

    exit

Sample Output:

    Saving changes… Thanks for using

# Command Summary

| Action | Format, Examples                                                                                                |
|--------|-----------------------------------------------------------------------------------------------------------------|
| Add    | `add n/NAME p/PHONE_NUMBER j/JOB [s/SKILLS]...` e.g: `add n/Bob p/99999999 j/Data Analyst s/Pandas s/Python s/Java `
| View   | `view n/NAME` e.g: `view n/Steve Jobs`                                                                              |
| List   | `list`                                                                                                            |
| Delete | `delete n/NAME` e.g: `delete n/Elon Musk`                                                                          |
| Exit   | `exit`                                                                                                            |



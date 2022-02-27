---
layout: page
title: User Guide
---

LinkedOUT is the only application that any experienced recruiter needs. LinkedOUT allows recruiters to keep track of many applicants, and the job they applied for. You can store their contact details, skills and the stage of their application, all in one place.

* TOC
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `linkedout.jar` from [here] (URL to be updated).

1. Copy the file to the folder you want to use as the _home folder_ for LinkedOUT.

1. Double-click the file to start the app.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

  * **`list`** : Lists all job applicants, along with an overview of each applicant.

  * **`add`**`n/Bob p/99999999 j/Data Analyst` : Adds an applicant named `Bob` to the list of applicants.

  * **`delete`**`Bob` : Deletes Bob from the list of applicants.

  * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [s/SKILLS]` can be used as `n/John Doe s/Python` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[s/SKILLS]…​` can be used as ` ` (i.e. 0 times), `s/Python`, `s/Python s/Java` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`

### Adding an applicant: `add`

Adds a new applicant to be tracked, by adding their name, phone number, the job they have applied for, along with a variable number of skills.

Format:

    add n/NAME p/PHONE_NUMBER j/JOB [s/SKILLS]...

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of skills (including 0)
</div>

Example:

    add n/Bob p/99999999 j/Data Analyst s/Pandas s/Python s/Java

Sample Output:

    Applicant Bob has been successfully added for job Data Analyst

### Listing all applicants : `list`

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

### Viewing a specific applicant : `view`

Allows the user to view an overview of a specific applicant, specified by applicant name

Format:

    view n/NAME

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`

Example:

    view n/Steve Jobs

Sample Output:

    Here’s an overview of Steve Jobs:
    
    Phone Number: 99999999
    
    Job Applied: CEO of Apple
    
    Skills: Product Design  

### Deleting an applicant : `delete`

Deletes a specific job applicant, specified by applicant name.

Format:

    delete n/NAME

* Delete is case-insensitive. e.g `hans` will delete `Hans`
* Only exact matches are deleted. e.g `Hans` will not delete `Hans Gruber`

Example:

    delete n/Elon Musk

Sample Output:

    Applicant Elon Musk has been deleted

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

LinkedOUT data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

LinkedOUT data is saved as a JSON file `[JAR file location]/data/linkedout.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, LinkedOUT will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LinkedOUT home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER j/JOB [s/SKILLS]...` e.g: `add n/Bob p/99999999 j/Data Analyst s/Pandas s/Python s/Java `
**Delete** | `delete n/NAME` e.g: `delete n/Elon Musk`
**View** | `view n/NAME` e.g: `view n/Steve Jobs`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
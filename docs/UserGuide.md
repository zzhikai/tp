---
layout: page
title: User Guide
---

LinkedOUT is the only application that any experienced recruiter needs. LinkedOUT allows recruiters to keep track of many applicants, and the job they applied for. You can store their contact details, skills and the round of their application, all in one place.

## Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Who is this User Guide for?

This user guide is meant for users who wish to learn how to use our application. It is best suited for recruiters who are looking to incorporate this app into their daily workflows.

If you would like to learn more about the technical aspects of our application instead, you can do so by reading our [Developer Guide](https://ay2122s2-cs2103t-t09-2.github.io/tp/DeveloperGuide.html).

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Legend

<div markdown="block" class="alert alert-info">

**:information_source: Notes:**
Notes are placed in this guide to assist you in interpreting the terminology used throughout this guide.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Tips are placed in this guide to serve as suggestions that you can try out while using our application.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Cautions are placed in this guide to serve as warnings for certain actions.
</div>

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `linkedout.jar` from [here](https://github.com/AY2122S2-CS2103T-T09-2/tp/releases/tag/v1.3(trial)).

1. Copy the file to the folder you want to use as the _home folder_ for LinkedOUT.

1. Double-click the file to start the app.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

  * **`list`** : Lists all job applicants, along with an overview of each applicant.

  * **`add`**`n/Bob p/99999999 e/bob@example.com j/Data Analyst r/Interview s/Pandas` : Adds an applicant named `Bob` to the list of applicants.

  * **`delete`**`INDEX` : Deletes the applicant from the list of applicants.

  * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [s/SKILLS]` can be used as `n/John Doe s/Python` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[s/SKILLS]…​` can be used as (i.e. 0 times), `s/Python`, `s/Python s/Java` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Adding an applicant: `add`

Allows you to add a new applicant to be tracked with the following details:
* Name
* Phone Number
* Email
* The job they've applied for
* Round of job application
* Particular skills they may have

Format:
```
add n/NAME p/PHONE_NUMBER e/EMAIL j/JOB r/ROUND [s/SKILLS]...
```
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of skills (including none at all)
</div>

Example:
```
add n/Bob p/99999999 e/bob@example.com j/Data Analyst r/Interview s/Pandas s/Python s/Java
```
Sample Output:
```
New applicant added: Bob; Phone: 99999999; Email: bob@example.com; Job: Data Analyst; Round: Interview; Skills: [Java][Pandas][Python]
```

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Listing all applicants : `list`

Allows you to list out all job applicants, along with an overview of each applicant.

Format:
```
list
```
Example:
```
list
```
Sample Output:
```
List of applicants:
1. Steve Jobs
Job Applied: Product Designer
Skills: Product Design
Phone Number: 99999999

2. Elon Musk
Job Applied: Software Developer
Skills: Pandas, Python
Phone Number: 88888888
```

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Viewing a specific applicant : `view`

Allows you to view an overview of a specific applicant, specified by an applicant's full name (exact match)

Format:
```
view NAME
```
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name is searched.
* Only full name will be matched e.g. `Han Lee` will not match `Han`
* Only exact full name with correct spacing will be matched e.g. `HanLee` will not match `Han Lee`

Example:
```
view Steve Jobs
```
Sample Output:
```
Here’s an overview of Steve Jobs:
Phone Number: 99999999
Job Applied: CEO of Apple
Skills: Product Design  
```

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Searching a specific applicant : `search`

Allows you to view an overview of a specific applicant, specified by an applicant's first or last name 

Format:
```
search n/NAME
```
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* First name/Last name will be matched e.g. `Han` will match `Han Lee`

Example:
```
search n/Steve
```
Sample Output:
```
Here’s an overview of Steve Jobs:
Phone Number: 99999999
Job Applied: CEO of Apple
Skills: Product Design  
```

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Editing an applicant : `edit`

Allows you to edit details of the applicant identified by the index number. The index number used corresponds to the one in the displayed applicant list. Existing values will be overwritten by the input values.

Format:
```
edit i/INDEX [n/NAME] [p/PHONE] [e/EMAIL] [j/JOB] [r/ROUND] [s/SKILL]...
```

* Only valid indexes are edited. e.g If there are only `4` applicants in LinkedOUT but `5` is specified, then the intended action will not be carried out.
* Only positive indexes are edited. e.g As we label our applicants starting from `1`, an index of `-1` will not be tagged to an applicant.

Example:
```
edit 1 p/91234567 e/johndoe@example.com
```
Sample Output:
```
Edited Applicant: David Lee; Phone: 91234567; Email: johndoe@example.com; Job: Risk Assessment Associate; Round: Stock Pitch Assessment; Skills: [Accounting][Equities][Cryptocurrency]
```

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Flagging an applicant : `flag`

Allows you to flag an applicant identified by the index number. The index number used corresponds to the one in the displayed applicant list. Flagged applicants will appear at the top of the list.

Format:
```
flag INDEX
```

* Only valid indexes are edited. e.g If there are only `4` applicants in LinkedOUT, but the index specified is `5`.
* Only positive indexes are edited. e.g As we label our applicants starting from `1`, an index of `-1` will not be tagged to an applicant.
* Flag acts like a toggle. To un-flag the applicant, you may simply re-type the same command.

Example:
```
flag 1
```
Sample Output:
```
Flagged Applicant: David Lee; Phone: 91234567; Email: johndoe@example.com; Job: Risk Assessment Associate; Round: Stock Pitch Assessment; Skills: [Accounting][Equities][Cryptocurrency]
```

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Deleting an applicant : `delete`

Allows you to delete a specific job applicant, specified by index.

Format:
```
delete i/INDEX
```

* Only valid index are deleted. e.g `5` will not delete if there are only `4` applicants in the list
* Only positive index are deleted. e.g `-3` will not delete the applicant

Example:
```
delete i/1
```
Sample Output:
```
Deleted Applicant: Bernice Yu; Phone: 99272758; Email: berniceyu@example.com; Job: Social Media Marketer; Round: Instagram Check; Skills: [Video Editing][Social Media Marketing]
```

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Exiting the program : `exit`

Exits the program.

Format: `exit`

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Saving the data

LinkedOUT data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---
### Editing the data file

LinkedOUT data is saved in `[JAR file location]/data/linkedout.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, LinkedOUT will discard all data and start with an empty data file on the next run.
</div>

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: You may first install the app in another computer. Then, you may overwrite the empty data file it creates with the file containing data from the previous LinkedOUT home folder.

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Command summary

| Action     | Format, Examples                                                                                                                                            |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**   | `help`                                                                                                                                                      |
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL j/JOB r/ROUND [s/SKILLS]...<br/>` <br/>e.g: `add n/Bob p/99999999 e/bob@example.com j/Data Analyst r/Interview s/Pandas` |
| **List**   | `list`                                                                                                                                                      |
| **View**   | `view NAME` e.g: `view Steve Jobs`                                                                                                                      |
| **Search** | `search n/NAME` e.g: `search n/Steve`                                                                                                                       |
| **Edit**   | `edit i/INDEX [n/NAME] ...` e.g: `edit 1 n/Elon Musk` 
| **Flag**   | `flag INDEX` e.g:  `flag 1`
| **Delete** | `delete i/INDEX` e.g: `delete 1`                                                                                                                            |                                                                                                           |
| **Exit**   | `exit`                                                                                                                                                      |

[Back to top <img src="images/back-to-top-icon.png" width="25px" />](#table-of-contents)

---

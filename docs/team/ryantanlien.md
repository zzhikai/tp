---
layout: page
title: Ryan Tan's Project Portfolio Page
---

### Project: LinkedOUT
***
LinkedOUT is the only application that any experienced recruiter needs. LinkedOUT allows recruiters to keep track of many applicants, and the job they applied for. You can store their contact details, skills and the round of their application, all in one place.

Given below are my contributions to the project.

### Contributions to Project
***
* **Code Contributions**:
  * [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ryantanlien&breakdown=true)
  
<br>

* **New Feature**: Added the ability to flag a particular applicant. (Pull requests [\#117](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/117), [\#128](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/128), [\#146](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/146))
  * What it does: It flags a particular applicant as important, pinning them to the top of the applicant list. The flag is indicated by a clean flag icon.
  * Why this feature is essential: It serves as a reminder for users to attend to this particular applicant, or that this applicant is a top choice for recruitment.
  * How this feature contributed to the project: It serves as a code template for other team members or alternative sorting implementation when implementing other sorting related functionalities.
  
<br>

* **Enchancements Implemented**:
  * Performed initial large-scale refactoring to starting AB3 project in file structure to pave foundation for AB3's modification into LinkedOUT.
    (Pull request [\#36](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/36)) 
    <br>
    These changes include:
    * Creating the applicant package in the model by moving `Person` related classes in the person package to the applicant package.
    * Writing the key data class: Applicant.
    * Changing all `Person`-related usages in code to `Applicant`.
    * Changing all `address`-related usages in code to `linkedout`.
    * Credits: Zhikai and Ryan Cheung for aiding in integration.
  * Modified Job and Round regular expressions to alphanumeric only in order to enforce stricter behavior in accordance with user expectation. Wrote extensive tests as well to support this change.
    (Pull requests [\#79](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/79), [\#148](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/148))
    
<br>

* **Documentation**:
  * **Contribution to the UG**:
    * Documented key steps and information for users to perform the flag operation. (Pull request [\#152](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/152))
    * Highlighted important behaviours of commands when interacting with one another. (Pull request [\#152](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/152))
    * Aided in general formatting of the user guide to ensure ease-of-use and readability.

  * **Contributions to the DG**:
    * Added implementation details for the flag feature.
    * Added key use case of viewing an individual applicant. (Pull request [\#55](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/55))
    * Helped to refactor pre-existing AB3 PUML files and diagrams.  (Pull request [\#36](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/97))

<br>

* **Contributions to team-based tasks**
  * All DevOps related contributions such as:
    * Setting up GitHub Team Organization.
    * Setting up GitHub Team Repo.
    * Setting up Jekyll and GitHub project website using GitHub Pages
  * Set up v1.3 Product Demo in project management document.
    
  
<br>

* **Review/mentoring contributions**
  * Reviewed team PRs to ensure proper code architecture (Pull request [\#121](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/121))
    and proper, extensive unit and integration testing is performed. (Pull request [\#153](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/153))
  * Aided in debugging and implementation issues when teammates were implementing their features, such as Kai Yi and Zhikai with search-sort. (Pull request [\#131](https://github.com/AY2122S2-CS2103T-T09-2/tp/pull/131)) 
  
  
<br>

* **Contributions beyond the project team**
  * Reported many non-trivial bugs (17 bugs in total, top 10% of bugs reported) that were accepted by other teams during acceptance testing phase. (Issue [\#12](https://github.com/ryantanlien/ped/issues/12), [\#14](https://github.com/ryantanlien/ped/issues/14))

# Module 1 - Coding Standards

## Reflection 1
This module, especially in exercise 1, gave me the chance to apply the concepts that were taught in class and in the module.
I was particularly fond of applying clean code principles to my code by making them as intuitive and as descriptive as they could be.
For instance, function names such as findById, update, and delete are variables names which are clear to me as they serve handle one well-defined goal of a product.
Another implementation of the module which I enjoyed was applying separation of concerns such as separating controller, model, repository, and service.
These helped me a lot since debugging an error would only require me to fix the exact file of where that bug occurred while maintaining readability between each package.

Other than clean code, I also managed to practice lessons from git flow and secure coding.
I learned that each feature should be its own branch to ensure modularity through git flow.
Applying secure coding taught me to think of possible cases where things may go unplanned.
Overall, I think I managed to apply the lessons from this module well so far. If I could change a few things and if I had more time, I may think of more cases to make sure my code is robust.

## Reflection 2

1. After working on unit tests, I realize that verifying that a software works is just as important as making the software.
As of now, I don't think there is a specific number for the amount of unit tests. However, I do think that unit tests should cover every or a lot of possibilities where situations can happen.
As for code coverage, while getting to 100% code coverage is nice, I think aiming for 80% or 90% code coverage is much more achievable.

2. I don't think the new functional test suite is quite clean. This newly described code may reduce code quality because it violates the DRY principle due to the same set-up procedure and the same instance variables. 
To solve this, I think the optimal solution would be to put all the set-up code in one parent class while making new tests in a different class.
This way, duplicate code is minimized while maintainability stays simple.

# Module 2 - CI/CD & DevOps
Link to the deployed website: hushed-hailee-kki-aldebaran-rahman-adhitya-2406365370-ec4340a3.koyeb.app/

## Reflection

1. One code quality issue that I have resolved so far is the redundant public keyword. 
Specifically, this redundancy initially happened because ProductService.java already has a public keyword tied to it so adding public to describe its logic is redundant. 
To resolve this, I removed the public keyword from the functions inside the interface and the issue was gone when I opened the PMD report file.
I think I can extend this methodology by checking the PMD report file and finding what needs to be fixed. This way, I can minimize issues one by one and develop a robust application.
2. So far, I think the current CI/CD workflow in this repository has met the definition of CI/CD.
The main reason for this is whenever I make a git push to a branch that has a .yml file, I can automate the work of verifying if things are working or not which counts as continuous integration.
This reduces the amount of time that I use to make sure my code is working and allows me to focus more on building software that is aligned with my plan. In this repository, I implemented that by using automated workflows through GitHub Actions which compiles the Java code, runs tests to verify the codebase, and ensures quality code using tools such as PMD and OSSF Scorecard. 
These verifications determine if a push can pass before merging into the main branch and therefore ensures the codebase stays stable. 
Another reason is pushing changes (which have been verified) to main automatically deploys those latest changes to the website which falls under continuous deployment.
This helps me a lot since I don't have to worry much about manually deploying the website every time I make a change. Whenever I make a push to main or a merge to main that has been verified by various workflows, Koyeb detects the change automatically, builds the application using Docker, and deploys it to a live server. 
This automated process, which happens without human intervention, counts as continuous deployment.
Overall, I learned quite a lot about CI/CD through this module. I'm glad that this repository has implemented it optimally.
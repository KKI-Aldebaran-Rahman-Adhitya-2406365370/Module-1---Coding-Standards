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
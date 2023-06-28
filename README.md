# Userinterface

## Task type
Test Automation, Web

### Task
Site: [https://userinyerface.com/](https://userinyerface.com/)

|Step|Expecting result|
|---|---|
|**Test case 1**|   |
|Navigate to home page.|Welcome page is open.|
|Click the link (in text 'Please click HERE to GO to the next page') to navigate the next page.|The '1' card is open.|
|Input random valid password, email, accept the terms of use and click "next" button.|The '2' card is open.|
|Choose 2 random interests, upload image, click "Next" button.|The '3' card is open.|
|**Test case 2**||
|Navigate to home page.|Welcome page is open.|
|Hide help form.|Form content is hidden.|
|**Test case 3**||
|Navigate to home page.|Welcome page is open.|
|Accept cookies.|Form is closed.|
|**Test case 4**||
|Navigate to home page.|Validate that timer starts from "00:00"|

- Validations must be performed using Asserts;
- Page object pattern is used for pages & elements description
- Use corporate web automation framework

### Run Test Command

>mvn test -Dbrowser={browser} -DtestEnv={testEnv} -Dlanguage={language}

    - browser = {chrome, firefox, edge}
    - testEnv = {dev, uat, prod}
    - language = {vietnamese, english}

## Convention

1. test.data.json.language || test.data.json.environment:
    - key must be lower case
    - word separator is "_"
    - no special character
2. 
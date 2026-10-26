In the constructor, you'll need to fill the map. The pseudocode might look something like this:

```
gameCounts = {}
while has next input:
    get name
    get score

    if name not in gameCounts:
        gameCounts.put(name, 1)
    else:
        oldCount = gameCounts.get(name)
        gameCounts.put(name, oldCount + 1)
```

Implementing gameCount will require:

1. Removing the UnsupportedException
2. uncommenting checkPerson
3. using gameCount (which will be accessible as an instance variable!) to get the value associated with the name

It will be a pretty short method!

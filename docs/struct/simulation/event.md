# Event class

## Static event generator

## Fields
- Event Id
- Participant count
- Participant list
- Event type
- Extroversion points cost
- Extroversion point successive tries coefficients
- Life drain
- Age range
- PickUppers count
- PickUppers men to women ratio

## EventMatch (People go in, couples go out)
- A group of people is selected as courtiers, of ratios according to the settings
- Each one of the courtiers selects how many tries they want (according to extroversion budget)
- Each of the courtiers selects a subset of the ?remaining? population of opposite sex of size of the previous diceroll
- They either get together or not. They stop trying after they do
    - Threshold is decided by cobining zodiac signs, ideal beaty thresholds
    - Courtier does a diceroll, if above threshold, advance succeeds
- The event returns an array of couples

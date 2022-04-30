# The Battle of the Sexes

### Definitions
A population is a set of individuals; individuals are grouped into
types. We define state of a type T the ratio between the number of individuals
of type T over the total number of individuals. The global state associates each
type with its state. We assume a notion of closeness is given: two population
are close if the difference between the values of their state is small enough to be
negligible.
Populations evolve according to (possibly non-deterministic) rules which
determine how the individuals of each type thrive (grow in their number) or
langiush (decrease) depending on the global state. An evolution trace is an infi-
nite sequence < p0, p1 , · · · > of pupulations, where p0 is called initial and each
pn+1 is obtained from pn by applying the evolution rules. We say that a trace
reaches stability if it includes a population pn such that for each m > n the state
of pm is close to that of pn .

### Four types of people
This project is inspired by Chapter 9 of The Selfish
Gene, a 1976 book by Richard Dawkins. The chapter describes “the battle of the
sexes”, where a model is provided of a population, featuring two male types, the
faithful (F ) and the philanderers (P ), and two female types, the coy (C) and
the fast (S), characterised as follows (the names are from the book):
- faithfull men: they are willing to engage in a long courtship and participate
in rearing their children;
- philanderers: reckless men, they don’t waste time in courting women: if not
immediately accepted, they move away and try somewhere else; moreover,
if accepted, they mate and then leave anyway, ignoring the destiny of their
children;
- coy women: they accept a partner only after a long courtship;
- fast women: if they feel so, they don’t mind copulating with whoever they
like, even if just met.

### The FPCS payoffs table
Let’s give names to the evolutionary payoffs in-
volved in the battle of the sexes:
- a : the evolutionary benefit for having a baby
- b : the cost of parenting a child
- c : the cost of courtship

Next we represent the payoffs resulting from a woman of type X engaging
with a man of type Y by means of a pair (x, y), where x is the payoff of X, and
y that of Y . According to the characters described above, the battle of the sexes
can be formalised as in the following matrix, which may be used in defining the
evolution rules.

|   | F                          | P          |
|---|----------------------------|------------|
| C | (a - b/2 - c, a - b/2 - c) |   (0, 0)   |
| S |     (a - b/2, a - b/2)     | (a - b, a) |

### Dawkins’ case study
Dawkins described the battle of the sexes by adopting
the following values: a = 15, b = 20 e c = 3. Here is the corrisponding FPCS
table:

|   | F      | P       |
|---|--------|---------|
| C | (2,2)  | (0,0)   |
| S | (5,5)  | (-5,15) |

When using these values, the system stabilises in a state where 5/6 of the
women are coy and 5/8 of the men are faithful. It is easy to check that this is
indeed a stable solution: given these ratios, the average gain of a coy woman,
that is 2 · 5/8, equals that of a fast, which is 5 · 5/8 − 5 · 3/8. Therefore none
of the two would have any interest in changing her strategy. And similarly for
men.

## Assignment
Implement in Java a simulator for the battle of the sexes.
The simulator must be parametric in the values of a, b and c. The rules of
evolution should be suitably defined according with the FPCS table. Given an
initial population, the simulator will iteratively apply the rules of evolution till,
if ever, an evolutionary stable state is reached, in which case the percentages of
the four types are returned as result. Ideally, independently of the initial state,
the simulator should compute the values discussed above. Or should it?
Together with the Java program, a short paper (approx 10 pages) is required,
describing the adopted model (e.g. the rules of evolution, the mechanism for
modelling how people match, etc.), the structure of the software, and discussing
some interesting case studies, which may be obtained by choosing different values
for the parameters a, b and c, or modifying the initial population.

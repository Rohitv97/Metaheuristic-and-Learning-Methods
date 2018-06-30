
# Grey Wolf Optimizer

Run Grey_wolf_optimizer_test.java

It calls the function Grey_wolf_optimizer()
Then calls solution()

## Functions

### * Grey_wolf_optimizer(function, Lower Limit, Upper Limit, Number of Iterations, Number of Wolves)
      * N = Number of Wolves
      * D = Upper.length
      * ff = function
      * Lower = Lower Limit
      * Upper = Upper Limit
      * maxiter = Number of Iterations
      * Initialises all variable a, A, C
      * Initialises

### * solution()
      * Calls init()
      * Repeat for maxiter
        * Set 'a' vector value
        * For every wolf
          * for every D
            * Find rand1 and rand2. Find A and C vectors. For Alpha, Beta and Delta, find X1, X2, X3
            * Call simplebounds(X1), simplebounds(X2), simplebounds(X3)
            * Calculation mean of X1,X2,X3
        * Call simplebounds() for full 2d array
        * Call sort_and_index() for full 2d array
        * Find new Alpha, Beta and Delta
        * Store BESTVAL as ff.func[first value of array]



### * init()
      * Sets a random value between Lower and Upper Limits for every wolf [Represented in 2d array]
      * call sort_and_index()
      * Set Alpha for first, Beta for second, Delta for third.

### * sort_and_index()
      * Sorts based on Fitness value and returns sorted values.

## * simplebounds()
      * Make modifications to 2d array based on its value. (?)

# Assignment 2

Please read Assignment-2.pdf for overall instructions first. This file explains
how to setup, run, and submit your code.

## Overview

Please read and follow all instructions below carefully.  If you have problems
or questions, talk to your professor.

To hand in your work, see the "Submitting your Assignment" section below.

## Setup

This assignment relies on a number of dependencies, which must be installed on
your computer.

First, install Node.js on your computer. See installation instructions at:

https://nodejs.org/en/

You can install the LTS (Long Term Support) version of node.js, which is
currently 10.15.0, although any 10.x.x version should work.

## Install Dependencies

Open a command line terminal and navigate (i.e., "cd") to the directory where
you have unzipped the assignment files. When you type "dir" (Windows) or
"ls" (Linux/macOS) you should see this README.md file, package.json, etc.

In this directory, install the assignment dependencies using the
Node.js Package Manager (npm), which is installed along with node.js.  In your
terminal, type the following:

```
npm install
```

This will download and save all the necessary files to a folder named
`node_modules/` in the current directory.

If you have trouble getting "npm install" to work:

* Did you install node.js?
* If you type "node --version" in your terminal, does it print the version?
* Are you in the right directory (you must cd into the correct directory)

If you need help, ask your classmates and/or talk to your professor.

## Learn how to Run the Assignment Bundler and Dynamic Web Server

This assignment includes multiple HTML, JS, and CSS files.  We're going to
be using ES Modules as well, some we'll write, and some we'll include from
npm.  To use these in the browser, we'll use the Parcel Bundler, see
https://parceljs.org/.

Parcel will read our source code, and combine all the JavaScript files into
one bundle (i.e., JavaScript file) that contains everything.  Parcel will also
start a web server that will watch our code for changes, recreate our bundle,
and serve the results on http://localhost:1234.

To run Parcel:

```
npm run parcel
Server running at http://localhost:1234
```

or

```
npm start
Server running at http://localhost:1234
```

This will start a web server at `http://localhost:1234`, which points to
our bundle in `dist/`.  Open your browser to point to this URL.

NOTE: you should disable your browser's network cache when developing locally.
See https://nicholasbering.ca/tools/2016/10/09/devtools-disable-caching/

## Learn how to Lint your Code

Linting helps to find and remove common errors in code, for example, missing
a semi-colon, or forgetting to declare a variable.

To run eslint, use the npm command:

```
npm run eslint
```

If there are no problems, there will be no output.  If there is any output,
pay attention to what it says, so you can make corrections.  For example:

```
assignment2/src/index.js
  18:9  error  'x' is defined but never used  no-unused-vars
```

Here, we see a lint error, which has various information:

1. The filename is listed first, `assignment2/src/index.js`
1. The line number is listed next: 18
1. The column number on line 18 is listed next: 9
1. The actual error or warning comes next: `error  'x' is defined but never used`
1. The rule name comes last: `no-unused-vars`.  You can lookup how to fix these errors using the rule name, for example: https://eslint.org/docs/rules/no-unused-vars

Your code should have no lint errors when you submit it.

## Learn how to Properly Format your Code

Source code needs to be properly structured, use consistent indenting, semi-colons,
etc.  Doing so makes it easier to understand, read, and debug your code.

Consider the following two functions:

```
// Improperly formatted and indented
function BaD(x){
if(          x> 10 ){
    return x;}
        return 0
        }

// Properly formatted and indented
function good(x) {
    if (x > 10) {
        return x;
    }
    return 0;
}
```

Your code must be properly and consistently formatted.  You can do it by hand,
or, you can use Prettier (https://prettier.io/) to do it automatically.

To use Prettier, use the npm command:

```
npm run prettier
```

This will rewrite your files in `src/` to use proper formatting.  NOTE:
running this command will overwrite your file, so make sure you have saved
your work before you run it.

## Submitting your Assignment

When you have completed your assignment, you need to prepare your submission.
To do so, use the npm command:

```
npm run prepare-submission
```

This will do a number of things automatically for you:

1. Run prettier on your assignment code, formatting it
1. Create a directory, `assignment2-submission/`
1. Copy your code, tests, and the package.json file into `assignment2-submission/`
1. Run eslint on your code and save the result to `assignment2-submission/eslint.log`
1. Create `assignment2-submission.zip` from the contents of `assignment2-submission/*`

You can upload and submit the `assignment2-submission.zip` to Blackboard.

## Discussion of Other Assignment Files

You may be wondering about some of the other files in this project.  While you
don't need to modify them, or use them directly, here is what each one does:

node_modules/ - the installed dependencies necessary to run prettier, eslint, etc., installed when your run `npm install`.

.cache/ - the intermediate files created by Parcel, and reused between builds

dist/ - the bundled/build files from Parcel

.eslintrc.js - configuration for eslint, see https://eslint.org/docs/user-guide/configuring

.npmrc - configuration settings for npm, see https://docs.npmjs.com/files/npmrc

.prettierrc - configuration settings for prettier, see https://prettier.io/docs/en/configuration.html

package.json - node.js package info, dependencies, build scripts, see https://docs.npmjs.com/files/package.json

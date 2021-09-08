package ua.training;

/*
// throws - correct
public class App {
    public static void main(String[] args) throws Throwable {}
}
*/

/*
// throws - incorrect, causes a compilation error: java: incompatible types: java.lang.String cannot be converted to java.lang.Throwable
public class App {
    public static void main(String[] args) throws String {}
}
*/

/*
// catch - correct
public class App {
    public static void main(String[] args) {
        try {
        } catch (Throwable t) {}
    }
}
*/

/*
// catch - incorrect, causes the same compilation error: java: incompatible types: java.lang.String cannot be converted to java.lang.Throwable
public class App {
    public static void main(String[] args) {
        try {
        } catch (String s) {}
    }
}
*/

/*
// throw - correct
public class App {
    public static void main(String[] args) {
        // Error is a child of Throwable
        throw new Error();
    }
}
*/

/*
// throw - incorrect, causes the same compilation error: java: incompatible types: java.lang.String cannot be converted to java.lang.Throwable
public class App {
    public static void main(String[] args) {
        throw new String("Hello!");
    }
}
*/

/*
// throw - incorrect, it requires a non-null argument and causes a runtime error: Exception in thread "main" java.lang.NullPointerException
public class App {
    public static void main(String[] args) {
        throw null;
    }
}
*/

/*
// throw - correct
public class App {
    public static void main(String[] args) {
        Error ref = new Error(); // creating an object
        throw ref;               // throwing an Error
    }
}
*/

/*
// throw - incorrect, causes a runtime error: Exception in thread "main" java.lang.StackOverflowError
public class App {
    public static void main(String[] args) {
        f(null);
    }
    public static void f(NullPointerException e) {
        try {
            throw e;
        } catch (NullPointerException npe) {
            f(npe);
        }
    }
}
*/

/*
// System.out is buffered, but System.err isn't. So the output here is:
// sout
// Exception in thread "main" java.lang.Error
public class App {
    public static void main(String[] args) {
        System.out.println("sout");
        throw new Error();
    }
}
*/

/*
// The next example (err before out in console) seems to be not working in 1.8 anymore
public class App {
    public static void main(String[] args) {
        System.out.println("sout");
        throw new Error();
    }
}
*/
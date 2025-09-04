# Design Patterns Implementation

This project demonstrates implementations of various design patterns in Java. Each pattern is implemented in its own package with clear examples and test cases.

## Table of Contents

- [Design Patterns Implementation](#design-patterns-implementation)
  - [Table of Contents](#table-of-contents)
  - [Project Structure](#project-structure)
  - [Design Patterns](#design-patterns)
    - [Creational Patterns](#creational-patterns)
    - [Structural Patterns](#structural-patterns)
    - [Behavioral Patterns](#behavioral-patterns)
  - [Getting Started](#getting-started)
  - [Running Tests](#running-tests)
  - [Contributing](#contributing)
  - [License](#license)

## Project Structure

The project is organized into three main categories of design patterns:

```
src/
├── Creational/
├── Structural/
└── Behavioral/
```

Each pattern is contained in its own subdirectory with related classes and a test class.

## Design Patterns

### Creational Patterns

Creational patterns are design patterns that deal with object creation mechanisms, trying to create objects in a manner suitable to the situation.

- **Abstract Factory** - Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
- **Builder** - Separates the construction of a complex object from its representation, allowing the same construction process to create various representations.
- **Factory Method** - Defines an interface for creating an object, but lets subclasses decide which class to instantiate.
- **Prototype** - Specifies the kinds of objects to create using a prototypical instance, and creates new objects by copying this prototype.
- **Singleton** - Ensures a class has only one instance, and provides a global point of access to it.

### Structural Patterns

Structural patterns are design patterns that ease the design by identifying a simple way to realize relationships between entities.

- **Adapter** - Converts the interface of a class into another interface clients expect.
- **Bridge** - Separates an object's interface from its implementation so the two can vary independently.
- **Composite** - Composes objects into tree structures to represent part-whole hierarchies.
- **Decorator** - Attaches additional responsibilities to an object dynamically.
- **Facade** - Provides a unified interface to a set of interfaces in a subsystem.
- **Flyweight** - Uses sharing to support large numbers of fine-grained objects efficiently.
- **Proxy** - Provides a surrogate or placeholder for another object to control access to it.

### Behavioral Patterns

Behavioral patterns are design patterns that identify common communication patterns between objects and realize these patterns.

- **Chain of Responsibility** - Passes a request along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
- **Command** - Encapsulates a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.
- **Interpreter** - Given a language, defines a representation for its grammar along with an interpreter that uses the representation to interpret sentences in the language.
- **Iterator** - Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
- **Mediator** - Defines an object that encapsulates how a set of objects interact.
- **Memento** - Without violating encapsulation, captures and externalizes an object's internal state so the object can be restored to this state later.
- **Observer** - Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically.
- **State** - Allows an object to alter its behavior when its internal state changes.
- **Strategy** - Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
- **Template Method** - Defines the skeleton of an algorithm in an operation, deferring some steps to subclasses.
- **Visitor** - Represents an operation to be performed on the elements of an object structure.

## Getting Started

To use these design patterns in your project:

1. Clone or download this repository
2. Navigate to the specific pattern directory you're interested in
3. Review the classes and test files to understand the implementation
4. Run the test files to see the pattern in action

Each pattern is implemented in a self-contained manner with clear comments explaining the purpose of each class and method.

## Running Tests

Each design pattern comes with a test class that demonstrates its usage. To run a test:

1. Compile the Java files:
   ```
   javac src/[Category]/[Pattern]/*.java
   ```

2. Run the test class:
   ```
   java [Category].[Pattern].[Pattern]Test
   ```

For example, to run the Iterator pattern test:
```
javac src/Behavioral/Iterator/*.java
java Behavioral.Iterator.IteratorTest
```

## Contributing

Contributions are welcome! If you'd like to add a new design pattern or improve an existing implementation:

1. Fork the repository
2. Create a new branch for your feature
3. Add your implementation following the existing structure
4. Include a test class demonstrating the pattern
5. Submit a pull request

Please ensure your code follows the existing style and includes appropriate comments.

## License

This project is available for educational purposes. Feel free to use and modify the code for learning and teaching design patterns.
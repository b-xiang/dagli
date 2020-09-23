package com.linkedin.dagli.annotation.struct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * The Struct annotation indicates that a class definition is meant to define the fields for a record (a struct), and
 * will be used to generate a class that extends this one and provides a range of useful autogenerated methods and inner
 * classes.
 *
 * The annotation processor will generate this new class with the name and package provided as the argument to the
 * annotation.  So, e.g. @Struct("com.xyz.UserData") will result in the generation of a class called "UserData" in the
 * package "com.xyz".  If you don't specify a package, the package of the @Struct-annotated class will be used.
 *
 * Suggestions for defining your @Struct-annotated class:
 * - This class serves only as the base class of the struct and is not intended for direct use.  It's thus a good idea
 *   to "hide" the class; making it package-private (and specifying the same package for the Struct) is recommended.
 * - The @Struct-annotated class and fields must be visible to the generated class!  Private, final and static fields
 *   will be ignored.  Package-private visibility for fields is recommended if the generated class will have the same
 *   package as the annotated class.
 * - Your @Struct-annotated class must also have a parameterless constructor that is visible to the generated class.
 * - If you want your @Struct to be serializable, implement Serializable and define the serialVersionUID for your
 *   class, e.g. "private static final long serialVersionUID = 1;"
 * - Use the "@Optional" attribute to mark fields that are not mandatory to set; i.e. client code can choose not to
 *   specify these fields, in which case the default value (or the value of whatever initializer expression you write)
 *   will be used instead.
 *
 * Here's an example that defines a struct with three fields (one optional) which will be generated in the current
 * package as a class called "Record":
 * <pre>
 * {@code
 * @Struct("Record")
 * class RecordBase implements Serializable {
 *   private static final long serialVersionUID = 1;
 *
 *   String name;
 *   int age;
 *   @Optional Locale preferredLocale = Locale.US;
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface Struct {
  /**
   * The package and class name for the class that will be generated from the @Struct annotated class.
   * A value of "com.xyz.SomeClass" will result in a class called "SomeClass" being generated in the "com.xyz" package.
   * If the package is omitted (e.g. just the value "SomeClass" is provided) the class will be generated in the current
   * package.
   */
  String value();
}
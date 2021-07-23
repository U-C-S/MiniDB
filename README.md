<h1 align=center>MiniDB</h1>

MiniDB is a type of [key-value](https://en.wikipedia.org/wiki/Key%E2%80%93value_database) like [XML Document Database](https://en.wikipedia.org/wiki/XML_database).

This is just a simple implementation, more-like a prototype and My first time doing it. So, The database design might not be as good and nor feature rich.

## About the Project

This is my college Project Submission for the following course

- Course Name: Object Oriented Programming using Java
- Course Code: IS4C04
- Course Instructor: `Mr. Suhaas KP`, Assistant Professor, Department of Information Science and Engineering, `The National Institute Of Engineering`, Mysuru 570008

### Made with
- OpenJDK 16.0.1
- Visual Studio Code
- A bit Powered by [Github-Copilot-preview](https://copilot.github.com/)

## Usage

Use the following commands to perform [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) operations on this NoSQL Database.

- `list` to list all the created databases.
- `new {name}` For creating a new database. (`{}` denotes that name is a variable)
- `use {name}` to select the database from the existing ones for CRUD operations.
- `drop {name}` to delete a database.
- `schema {}` for declaring schema of the database. (NOTE: schema must be declared first to add any data)
- `add {}` for adding data to the database. the data is seperated with `,` and must follow the schema.
- `read` to show the all data of the database.
- `delete {id}` for deleting data which has specified id. 

### Example usage

After starting the CLI, you should see the following:

```
--------------- Welcome to MiniDB ---------------
--------- Made by Chanakya. MIT License ---------
Enter the Commands: (Use 'exit;' to exit the cli)
```

The following commands creates a database named `animal`. We declare a schema first since it's important to have a format of data to store in database. Then, we add data that must satisfy the schema format.

```
new animal
schema id,name,legs
add 1,cat,4
add 2,fish,0
```

Note: The first element is taken as the id, which is used as a pointer to data. So, it's a good practice to name it as `id` itself.

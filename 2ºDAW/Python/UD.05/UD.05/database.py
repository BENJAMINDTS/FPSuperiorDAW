import mysql.connector

def connect_to_database():
    mydb = mysql.connector.connect(
        host="localhost",
        user="root",
        password="", # Remember to add your actual password if you have one set
        database="python"
    )
    mycursor = mydb.cursor()
    print("Successfully connected to the database!")
    
    # Returning the connection and cursor so you can use them elsewhere
    return mydb, mycursor
  
def disconnect_from_database(mydb, mycursor):
    mycursor.close()
    mydb.close()
    print("Database connection closed.")

# Calling the function to test the connection
db, cursor = connect_to_database()
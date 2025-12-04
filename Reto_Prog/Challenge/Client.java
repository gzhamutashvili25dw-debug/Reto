package reto1.Challenge;

public class Client {

        private String dni;
        private String firstName;
        private String lastName;
        private String username;
        private String password;

        public Client(String dni, String firstName, String lastName, String username, String password) {
            this.dni = dni;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
        }

        public String getDni() { return dni; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }

        public void setUsername(String username) { this.username = username; }
        public void setPassword(String password) { this.password = password; }

        @Override
        public String toString() {
            return "Traveller [DNI: " + dni + ", Name: " + firstName + " " + lastName + ", Username: " + username + "]";
        }
    }


package gb.consolidation.lesson1.task1;

public class Person {

    private final String firstName;

    private final String lastName;

    private final String middleName;

    private final String country;

    private final String address;

    private final String phone;

    private final int age;

    private final String gender;

    private Person(String firstName, String lastName, String middleName, String country, String address, String phone, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {

        private String firstName;

        private String lastName;

        private String middleName;

        private String country;

        private String address;

        private String phone;

        private int age;

        private String gender;

        private Builder() {
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder withMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }
        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }
        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder withAge(int age) {
            this.age = age;
            return this;
        }
        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(firstName, lastName, middleName, country, address, phone, age, gender);
        }

    }
}

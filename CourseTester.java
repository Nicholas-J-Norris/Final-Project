package finalProjectNorris;

public class CourseTester {
	public static void main(String[] args) {
		//creates a course object 
		Course dataStructures = new Course(117, "Data Structures", "Dr. Michelle Ruse", 21184, 3);

		//these students will be added to the roster
		Student nickNorris = new Student("Nick Norris", 152, "BIS", 32);
		Student jollyRoger = new Student("Jolly Roger", 322, "MIS", 49);
		Student artourBabaev = new Student("Artour Babaev", 1000, "BIS", 16);
		//these students will go into the waiting list
		Student waitzAlot = new Student("Waitz Alot", 1001, "last", 14);
		Student waitzMore = new Student("Waitz More", 1002, "lastest", 10);
		//this student will test the find method		
		Student notReal = new Student("Not real", 23, "Not Real", 23);
		//checks the add student method, to see if the method handles to many students
		Student notEnough = new Student("Build more Pylons", 1, "more", 2);
		//add students to class
		dataStructures.addStudent(artourBabaev);
		dataStructures.addStudent(nickNorris);
		dataStructures.addStudent(jollyRoger);
		//add students to waiting list
		dataStructures.addStudent(waitzAlot);
		dataStructures.addStudent(waitzMore);
		dataStructures.addStudent(notReal);
		dataStructures.addStudent(notEnough);
		//show the contents of the course
		dataStructures.display();
		//sort the roster
		dataStructures.insertionSort();
		//show sorted roster
		dataStructures.display();
		//delete 2 students
		boolean deleted = dataStructures.delete(artourBabaev);
		boolean deleted2 = dataStructures.delete(notReal);
		
		if(!deleted2) {
			System.out.println("Students that are not real, can not be deleted");
		}
		
		if(deleted) {
			System.out.println("student deleted: " + artourBabaev.name);
		}
		//tests the find method, by passing a student that is not in the waitng list
		int found = dataStructures.find(notReal);
		
		if(found == dataStructures.size()) {
			System.out.println("Student not in the roster of class");
		}
		//takes a student from the waiting list and puts them in the roster
		dataStructures.addStudent(dataStructures.remove());
		
		dataStructures.display();
	}
	

}

package finalProjectNorris;

public class Course {

	
	//the properties of the course class
	private int classroomNumber;
	private String name;
	private String teacher;
	private int courseRegistrationNumber;
	private int courseSize;
	private int numberStudents = 0;
	private Student[] roster; 
	//we will need a queue data structure for the waiting list
	private int front;
	private int rear;
	private int studentsWaiting = 0;
	private Student[] waitingList;

	public Course() {
		//default non arg constructor
	}

	public Course(int cn, String n, String t, int crn, int s) {
		this.classroomNumber = cn;
		this.name = n;
		this.teacher = t;
		this.courseRegistrationNumber = crn;
		this.courseSize = s;
		//both the size for the roster, and the waitinglist will be the same
		roster = new Student[s];
		waitingList = new Student[s];
		this.front = 0;
		this.rear = -1;
	}
	//only sorts the course array 
	public void insertionSort() {
		int in, out;
		for(out =1; out< this.numberStudents; out++) { //out = 1, in = null
			Student temp = roster[out]; //temp = student at index 1
			in = out;
				
			while(in > 0 && roster[in-1].name.compareToIgnoreCase(temp.name) > 0) {
				roster[in] = roster[in-1];
				--in;
			}

			roster[in] = temp;

		}
	}
	//users pass in a student object
	public void addStudent(Student stu) {
		//if there is space in the roster array
		if(this.numberStudents < this.courseSize) 
		{
			roster[numberStudents] = stu; //add student to roster
			numberStudents++;
			System.out.println(stu.name + " added to class");
		}else {//if the roster is full
			if(studentsWaiting < this.courseSize) //when there is room in the waiting list for more students
			{ 
				if(rear == this.courseSize) { 
					rear = -1;
				}
				waitingList[++rear] = stu; //add student to the list
				studentsWaiting++;
				System.out.println(stu.name + " added to waiting list and is " + this.studentsWaiting + 
						" in line to be placed in this class");
			}
			else { //if there is not enough space in the waiting list or the roster
				System.out.println("Not enough space in waiting list to add: " + stu.name);
			}
		}
	}
	//this method will only be able to search the class array, it can not search the waitinglist
	public int find(Student student) {
		//uses a binary search to go through the roster
		int lowerBound = 0;
		int upperBound = this.courseSize -1;
		int curIn;


		while(true) {
			curIn = (lowerBound + upperBound)/2;
			if(roster[curIn] == student) {
				return curIn;
			}
			else if(lowerBound > upperBound) { //if the student can not be found, the size is returned
				return this.courseSize;
			}
			else {
				int result = student.name.compareTo(roster[curIn].name);
				if(result > 0) {
					lowerBound = curIn + 1;
				}
				else {
					upperBound = curIn - 1;
				}
			}
		}

	}
	
	public int size() { //used to check for a negative result from the find method
		return this.courseSize;
	}
	
	public Student remove() {
		//takes a student out of the queue, and returns the object
		Student temp = waitingList[front++];
		
		if(front == this.courseSize) {
			front = 0;
		}
		studentsWaiting--;
		
		return temp;
		
	}
	//completely removes the student from the roster
	public boolean delete(Student student) {
		int j = find(student); //calls on the find method, and returns the index of the object
		if(j== this.courseSize) {
			return false; // in the event that the index is the course size, then the student is not in the roster
		}
		for(int k = j; k < this.numberStudents; k++) { //removes the student from the roster
			roster[k] = roster[k+1];
			numberStudents--;
		} 


		return true;
	}

	public void display() { //prints out the contents of the course object, and roster
		System.out.println("Class: "+ this.name);
		System.out.println("Classroom: "+ this.classroomNumber);
		System.out.println("Teacher: "+ this.teacher);
		System.out.println("Course Registration Number: "+ this.courseRegistrationNumber);
		System.out.println("Size: "+ this.courseSize);
		System.out.print("roster : ");

		for(int j = 0; j<this.courseSize; j++) {

			System.out.print(roster[j].name + ", ");
		}
		//tells the user what the first studnet in the waiting list is 
		System.out.println("First Student in waiting list: " + waitingList[front].name);//
		
		System.out.println("");
	}
}

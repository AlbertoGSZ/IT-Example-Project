# IT-Example-Proyect || Entity relationships


### **Relationships**

Three entities are found, to be called "Person", "CaseReport" and "ResourceURL".

The entity Person has reflexive relationship because it obeys a structure
of a tree similar to the genealogical one, in which one figure leaves several in branched structure.
Each person can have a boss or not as well as a list of people under his command (if any).
In the same way, the ¨Person¨ entity will have a list of ¨CaseReport¨ objects that will be the criminal history.

"CaseReport" entity has a _many to many_ relationship with "ResourceURL" (which contains a URL to a resource in another site) as a "CaseReport" can have many URLs related and those URLs can be related to many "CaseReports".


### **Entity info: Person**

- id: Long. Non-repeatable random generation.
- name: String.
- surname: String.
- birthDate: Instant. Requirement date before the present day (have to consider the limits).
- adress: String.  
- nationality: ENUM.
- sex: ENUM.
- status: ENUM.
- alias: String.
- felonyRecordsDigital: CaseReport list.
- rank: Integer.
- chief: Person.
- subordinates: Person list.
- preIncarcerationRegistry preIncarcerationRegistry

Because of logic reasons, I only wanted to designate the presence of a crime as necessary to create a Person object since in many cases not few data will be missing, but we will only have a crime that has drawn attention and the need to create a criminal record, without achieving to know even the sex of the criminal.

For the same reason I didn´t want to get deeper into certain aspects such as the structural configuration of the DNI (different in each country if there is one), the residence (sometimes you can have an exact location, coordinates of an unnamed site or a general site...), so if I usually lack details that allow me to configure the creation of the object, I prefer to sin cautiously and not delimiting supported by personal assumptions.


### **Entity info: CaseReport**

- id: Long. Non-repeatable random generation.
- date: Instant. (Creation of the CaseReport object)
- mugshotURL: String.
- personDetails: String. Not empty.  
- eventDescription: String. Not empty.
- evidencePhotosURL: String list.
- felonyRecordPDFsURLs: String list.


### **Entity info: PreIncarcerationRegistry**

- id: Long. Non-repeatable random generation.
- rank: Integer.
- oldChief: Long.
- oldSubordinates: Long List.



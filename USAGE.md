## Campus Course & Records Manager (CCRM) — Usage Guide

### 1. Prerequisites

Before running CCRM, ensure you have the following:

- **Java JDK** (version 17 or above, recommended: JDK 21)  
  Check with:
  ```bash
  java -version
  ```
- **VS Code / Eclipse / IntelliJ IDEA** installed.
- Git (optional) if you want to clone this repo.

---

### 2. Cloning or Downloading the Project

#### Option A — Clone via Git:
```bash
git clone https://github.com/your-username/CCRM.git
```

#### Option B — Download ZIP:
1. Go to the GitHub repo.
2. Click **"Code" → "Download ZIP"**.
3. Extract the project to your workspace.

---

### 3. Project Structure
Refer to the `README.md` for a detailed project structure explanation.

---

### 4. Running the Application

#### Using VS Code:
1. Open project folder in VS Code.
2. Ensure Java extension is installed.
3. Open `src/edu/ccrm/cli/Main.java`.
4. Run via:
   - Right-click → **Run Java**  
   OR  
   - Press **Ctrl + F5**.

#### Using Terminal:
1. Navigate to project root.
2. Compile:
   ```bash
   javac -d bin src/edu/ccrm/cli/Main.java
   ```
3. Run:
   ```bash
   java -cp bin edu.ccrm.cli.Main
   ```

---

### 5. CLI Menu Overview

Once executed, the program displays a command-line menu:

```
--- Main Menu ---
1. Student Management
2. Course Management
3. Enrollment Management
4. Transcript Management
5. Import/Export Data
6. Backup & Utilities
7. Reports
0. Exit
Enter choice:
```

Each option has its own submenu.

---

### 6. Example Usage Flow

#### Adding a Student:
```
Enter choice: 1
Enter ID: 24BEY0000
Enter full name: John doe
Enter email: John doe@example.com
Enter date of birth (YYYY-MM-DD): 2005-06-15
Enter registration number: 24BEY0000
```

#### Adding a Course:
```
Enter choice: 2
Enter course code: CSE1001
Enter course title: Programming in Java
Enter credits: 3
Enter instructor name: Prof. Sharma
Enter semester (SPRING, SUMMER, FALL): SPRING
Enter department: Computer Science
```

#### Enrolling Student:
```
Enter choice: 3
Enter student ID: 24BEY0000
Enter course code: CSE1001
```

#### Recording Grade:
```
Enter choice: 3
Enter student ID: 24BEY0000
Enter course code: CSE1001
Enter grade (S, A, B, C, D, E, F): A
```

#### Viewing Transcript:
```
Enter choice: 4
Enter student ID: 24BEY0000
```

---

### 7. Import/Export Data
- Automatically generates export files in:
  ```
  exports/YYYY-MM-DD-HH-MM/
  ```
- Contains:
  - `students_export.csv`
  - `courses_export.csv`
  - `enrollments_export.csv`

---

### 8. Backup & Utilities
- Creates backups with timestamp folders inside `exports/`.
- CSV backups contain full system data.

---

### 9. Reports
- GPA reports.
- Grade listings.
- Transcript outputs.

---

### 10. Error Handling
The system will display meaningful error messages for:
- Duplicate student or course entries.
- Invalid grade input.
- Enrollment conflicts.

---

### 11. Notes
- Keep `exports/` and `test-data/` directories intact for proper functionality.
- Do not delete `.gitkeep` files — they maintain empty folder structures in Git.

---

💡 **Tip:** Always test after making changes to ensure stability.

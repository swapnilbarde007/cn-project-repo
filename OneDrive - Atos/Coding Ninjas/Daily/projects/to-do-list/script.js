const TASK_STATUS_NOT_STARTED = "NOT STARTED";
const TASK_STATUS_IN_PROGRESS = "IN PROGRESS";
const TASK_STATUS_COMPLETED = "COMPLETED";
const TASK_STATUS_WONT_DO = "WONT DO";

class Task {
  taskId;
  taskName;
  status;
  constructor(taskId, taskName, status) {
    this.taskId = taskId;
    this.taskName = taskName;
    this.status = status;
  }

  equals(task1, task2) {
    if (
      task1.taskId === task2.taskId &&
      task1.taskName === task2.taskName &&
      task1.status === task2.status
    ) {
      return true;
    } else {
      return false;
    }
  }

  toString() {
    console.log(
      `Task ID: ${this.taskId}|Task Name: ${this.taskName}|Status: ${this.status}`
    );
  }

  get taskId() {
    return this.taskId;
  }
  set taskId(id) {
    this.taskId = id;
  }
  get taskName() {
    return this.taskName;
  }
  set taskName(taskName) {
    this.taskName = taskName;
  }
  get status() {
    return this.status;
  }
  set status(status) {
    this.status = status;
  }
}

class AllTasks extends Task {
  static initTaskId = 101;
  static taskArray = [];

  constructor() {}

  static getCurrentTaskId() {
    return this.initTaskId;
  }

  static eventDispatcher() {
    const event = new CustomEvent("refreshUI", { response: 0 });
    console.log("Event sent from AllTasks to TaskDOMManager");
    window.dispatchEvent(event);
  }

  static addTask(task) {
    AllTasks.initTaskId++;
    this.taskArray.push(task);
    AllTasks.eventDispatcher();
  }

  static get taskArray() {
    return AllTasks.taskArray;
  }

  static removeTask(task) {
    let foundTask;
    if (AllTasks.taskArray.length > 0) {
      foundTask = AllTasks.taskArray.findIndex((item) => {
        return item.equals(item, task);
      });
    }

    if (foundTask != -1) {
      AllTasks.taskArray.splice(foundTask, 1);
      //console.log("After deleting: " + AllTasks);
      this.eventDispatcher();
    }
  }

  static removeTaskById(id) {
    const foundItem = AllTasks.taskArray.findIndex((item) => {
      return item.taskId === parseInt(id);
    });
    if (foundItem != -1) {
      AllTasks.taskArray.splice(foundItem, 1);
    }

    this.eventDispatcher();
  }

  getTaskByName(taskName) {
    this.taskArray.filter((item) => {
      return item.taskName.includes(taskName);
    });
  }
  get taskArray() {
    return this.taskArray;
  }
}

class TaskDOMManager {
  ele_txtInputToDo;
  ele_btnAddTask;
  ele_toDoListItems;
  constructor() {
    this.ele_txtInputToDo = document.getElementById("txtInputToDo");
    this.ele_btnAddTask = document.getElementById("btnAddTask");
    this.ele_toDoListItems = document.getElementById("to-do-list-items");
    window.addEventListener("refreshUI", () => {
      console.log("Event received in TaskDOMManager");
      this.refreshTasks();
    });

    this.ele_btnAddTask.addEventListener("click", (event) => {
      console.log(this.ele_txtInputToDo.value);
      const task = new Task(
        AllTasks.getCurrentTaskId(),
        this.ele_txtInputToDo.value,
        TASK_STATUS_NOT_STARTED
      );
      AllTasks.addTask(task);
      console.log(AllTasks.taskArray);
    });
  }

  refreshTasks() {
    while (this.ele_toDoListItems.hasChildNodes()) {
      this.ele_toDoListItems.removeChild(this.ele_toDoListItems.firstChild);
    }
    AllTasks.taskArray.forEach((item) => {
      /*<!-- <div class="todo-list-container">
        <input type="radio" name="selectedTask" id="rd1">
        <div class="to-do-task">Coding Ninjas Study</div>
        <i class="fa-solid fa-circle-xmark"></i>
    </div>*/

      const taskLineItem = document.createElement("div");
      taskLineItem.setAttribute("class", "todo-list-container");
      taskLineItem.setAttribute("id", `_${item.taskId}`);

      const radioItem = document.createElement("input");
      radioItem.type = "radio";
      radioItem.name = "selectedTask";
      radioItem.id = "rd1";

      const taskNameEle = document.createElement("div");
      taskNameEle.classList.add("to-do-task");
      taskNameEle.textContent = item.taskName;

      const faCross = document.createElement("i");
      faCross.classList.add("fa-solid");
      faCross.classList.add("fa-circle-xmark");

      faCross.addEventListener("click", (event) => {
        console.log(event.target.parentElement.id.slice(1));
        AllTasks.removeTaskById(event.target.parentElement.id.slice(1));
      });

      taskLineItem.appendChild(radioItem);
      taskLineItem.appendChild(taskNameEle);
      taskLineItem.appendChild(faCross);

      this.ele_toDoListItems.appendChild(taskLineItem);
    });
    document.getElementById(
      "task-count"
    ).textContent = `${AllTasks.taskArray.length} tasks left`;
  }

  clearAllChildTask() {
    while (this.ele_toDoListItems.hasChildNodes) {
      this.ele_toDoListItems.children.remove();
    }
  }
}

const DOMParent = new TaskDOMManager();

const task1 = new Task(
  AllTasks.getCurrentTaskId(),
  "Task1",
  TASK_STATUS_NOT_STARTED
);
AllTasks.addTask(task1);
const task2 = new Task(
  AllTasks.getCurrentTaskId(),
  "Task2",
  TASK_STATUS_NOT_STARTED
);
AllTasks.addTask(task2);
const task3 = new Task(
  AllTasks.getCurrentTaskId(),
  "Task3",
  TASK_STATUS_NOT_STARTED
);

AllTasks.addTask(task3);
console.log(AllTasks.taskArray);
console.log("Before delete");
AllTasks.taskArray.forEach((item) => {
  item.toString();
});

AllTasks.removeTask(task2);
console.log("After delete");
AllTasks.taskArray.forEach((item) => {
  item.toString();
});

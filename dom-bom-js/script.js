const input = document.querySelector('#task-input');
const addBtn = document.querySelector('#add-btn');
const list = document.querySelector('#task-list');
const counter = document.querySelector('#counter');

let tasks = JSON.parse(localStorage.getItem('tasks')) || [];

function saveTasks() {
  localStorage.setItem('tasks', JSON.stringify(tasks));
}

function updateCounter() {
  let done = tasks.filter(function (t) { return t.done; }).length;
  counter.textContent = 'Виконано ' + done + ' з ' + tasks.length;
}

function renderTasks() {
  list.innerHTML = '';

  for (let i = 0; i < tasks.length; i++) {
    let task = tasks[i];

    let li = document.createElement('li');
    if (task.done) li.classList.add('done');
    li.dataset.id = task.id;

    let span = document.createElement('span');
    span.textContent = task.text;

    let btn = document.createElement('button');
    btn.textContent = '✕';
    btn.classList.add('delete-btn');

    li.appendChild(span);
    li.appendChild(btn);
    list.appendChild(li);
  }

  updateCounter();
}

function addTask() {
  let text = input.value.trim();
  if (text === '') return;

  tasks.push({
    id: Date.now(),
    text: text,
    done: false
  });

  saveTasks();
  renderTasks();
  input.value = '';
}

addBtn.addEventListener('click', addTask);

input.addEventListener('keydown', function (e) {
  if (e.key === 'Enter') addTask();
});

// делегування — один обробник на весь список
list.addEventListener('click', function (e) {
  let li = e.target;
  while (li && li.tagName !== 'LI') {
    li = li.parentNode;
  }
  if (!li) return;

  let id = Number(li.dataset.id);

  if (e.target.classList.contains('delete-btn')) {
    tasks = tasks.filter(function (t) { return t.id !== id; });
  } else {
    let task = tasks.find(function (t) { return t.id === id; });
    if (task) task.done = !task.done;
  }

  saveTasks();
  renderTasks();
});

renderTasks();

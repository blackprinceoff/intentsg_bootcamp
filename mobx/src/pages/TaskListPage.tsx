import { observer } from 'mobx-react-lite'
import { Link } from 'react-router-dom'
import taskStore from '../store/taskStore'

const TaskListPage = observer(function TaskListPage() {
  function handleKeyDown(e: React.KeyboardEvent) {
    if (e.key === 'Enter') taskStore.addTask()
  }

  return (
    <div className="container">
      <Link to="/" className="back-link">← На головну</Link>

      <h1>Мої задачі</h1>

      <div className="input-wrap">
        <input
          type="text"
          placeholder="Нова задача..."
          value={taskStore.inputValue}
          onChange={e => taskStore.setInputValue(e.target.value)}
          onKeyDown={handleKeyDown}
        />
        <button onClick={() => taskStore.addTask()}>Додати</button>
      </div>

      <p className="counter">Виконано {taskStore.doneCount} з {taskStore.tasks.length}</p>

      <ul className="task-list">
        {taskStore.tasks.map(task => (
          <li key={task.id} className={'task-item' + (task.done ? ' done' : '')}>
            <span className="task-text" onClick={() => taskStore.toggleTask(task.id)}>
              {task.text}
            </span>
            <Link to={'/tasks/' + task.id} className="task-link">→</Link>
            <button className="delete-btn" onClick={() => taskStore.deleteTask(task.id)}>✕</button>
          </li>
        ))}
      </ul>
    </div>
  )
})

export default TaskListPage

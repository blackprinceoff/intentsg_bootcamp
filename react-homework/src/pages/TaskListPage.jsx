import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'

function TaskListPage() {
  const [tasks, setTasks] = useState(() => {
    const saved = localStorage.getItem('tasks')
    return saved ? JSON.parse(saved) : []
  })
  const [inputValue, setInputValue] = useState('')

  useEffect(() => {
    localStorage.setItem('tasks', JSON.stringify(tasks))
  }, [tasks])

  const doneCount = tasks.filter(t => t.done).length

  function addTask() {
    const text = inputValue.trim()
    if (text === '') return

    setTasks([...tasks, { id: Date.now(), text: text, done: false }])
    setInputValue('')
  }

  function handleKeyDown(e) {
    if (e.key === 'Enter') addTask()
  }

  function toggleTask(id) {
    setTasks(tasks.map(t =>
      t.id === id ? { ...t, done: !t.done } : t
    ))
  }

  function deleteTask(id) {
    setTasks(tasks.filter(t => t.id !== id))
  }

  return (
    <div className="container">
      <Link to="/" className="back-link">← На головну</Link>

      <h1>Мої задачі</h1>

      <div className="input-wrap">
        <input
          type="text"
          placeholder="Нова задача..."
          value={inputValue}
          onChange={e => setInputValue(e.target.value)}
          onKeyDown={handleKeyDown}
        />
        <button onClick={addTask}>Додати</button>
      </div>

      <p className="counter">Виконано {doneCount} з {tasks.length}</p>

      <ul className="task-list">
        {tasks.map(task => (
          <li key={task.id} className={'task-item' + (task.done ? ' done' : '')}>
            <span className="task-text" onClick={() => toggleTask(task.id)}>
              {task.text}
            </span>
            <Link to={'/tasks/' + task.id} className="task-link">→</Link>
            <button className="delete-btn" onClick={() => deleteTask(task.id)}>✕</button>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default TaskListPage

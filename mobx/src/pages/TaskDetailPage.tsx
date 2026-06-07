import { observer } from 'mobx-react-lite'
import { useParams, Link } from 'react-router-dom'
import taskStore from '../store/taskStore'

const TaskDetailPage = observer(function TaskDetailPage() {
  const { taskId } = useParams()

  const task = taskStore.getTaskById(taskId!)

  if (!task) {
    return (
      <div className="container">
        <h1>Задачу не знайдено</h1>
        <p>Можливо, вона була видалена.</p>
        <Link to="/tasks" className="back-link">← Повернутися до задач</Link>
      </div>
    )
  }

  return (
    <div className="container">
      <Link to="/tasks" className="back-link">← Назад до задач</Link>

      <h1>{task.text}</h1>

      <span className={'detail-status ' + (task.done ? 'done' : 'pending')}>
        {task.done ? 'Виконано' : 'Не виконано'}
      </span>

      <p className="detail-info">
        Створено: {new Date(task.id).toLocaleString('uk-UA')}
      </p>
    </div>
  )
})

export default TaskDetailPage

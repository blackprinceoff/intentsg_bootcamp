import { Link } from 'react-router-dom'

function HomePage() {
  return (
    <div className="container">
      <div className="home-center">
        <h1>Мої задачі</h1>
        <p>Додавайте задачі та стежте за прогресом</p>
        <Link to="/tasks">Перейти до задач →</Link>
      </div>
    </div>
  )
}

export default HomePage

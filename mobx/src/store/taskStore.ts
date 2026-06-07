import { observable, computed, action, reaction, makeObservable } from 'mobx'

class TaskStore {
  @observable tasks: Array<{ id: number; text: string; done: boolean }> = []
  @observable inputValue = ''

  constructor() {
    makeObservable(this)

    const saved = localStorage.getItem('tasks')
    if (saved) {
      this.tasks = JSON.parse(saved)
    }

    reaction(
      () => JSON.stringify(this.tasks),
      (json) => localStorage.setItem('tasks', json)
    )
  }

  @computed get doneCount() {
    return this.tasks.filter(t => t.done).length
  }

  @action setInputValue(value: string) {
    this.inputValue = value
  }

  @action addTask() {
    const text = this.inputValue.trim()
    if (text === '') return

    this.tasks.push({ id: Date.now(), text: text, done: false })
    this.inputValue = ''
  }

  @action toggleTask(id: number) {
    const task = this.tasks.find(t => t.id === id)
    if (task) {
      task.done = !task.done
    }
  }

  @action deleteTask(id: number) {
    this.tasks = this.tasks.filter(t => t.id !== id)
  }

  getTaskById(id: string) {
    return this.tasks.find(t => String(t.id) === id)
  }
}

const taskStore = new TaskStore()
export default taskStore

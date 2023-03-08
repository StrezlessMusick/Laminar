package com.raquo.laminar.fixtures.example.components

import com.raquo.laminar.api.L._
import com.raquo.laminar.CollectionCommand.Append

class TaskList {

  private val taskDiffBus = new EventBus[CollectionCommand[Node]]

  private val showAddTaskInputBus = new EventBus[Boolean]

  private val showAddTaskInputSignal = showAddTaskInputBus.events.toSignal(false)

  private var count = 0

  val node: Div = div(
    h1("TaskList"),
    children.command <-- taskDiffBus.events,
    child.maybe <-- maybeNewTask,
    child.maybe <-- maybeNewTaskButton
  )

  def maybeNewTaskButton: Signal[Option[Node]] = {
    showAddTaskInputSignal.map { showAddTaskInput =>
      val showNewTaskButton = !showAddTaskInput
      if (showNewTaskButton) {
        count += 1
        Some(
          button(
            onClick.map(_ => Append(div("hello"))) --> taskDiffBus,
            //        onClick --> (true, sendTo = showAddTaskInputSignal),
            "Add task"
          )
        )
      } else {
        None
      }
    }
  }

  def maybeNewTask: Signal[Option[Node]] = {
    showAddTaskInputSignal.map { showAddTaskInput =>
      if (showAddTaskInput) {
        Some(button(
          onClick.mapTo(false) --> showAddTaskInputBus,
          "Cancel"
        ))
      } else {
        None
      }
    }
  }
}

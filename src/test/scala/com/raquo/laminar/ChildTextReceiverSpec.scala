package com.raquo.laminar

import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec

class ChildTextReceiverSpec extends UnitSpec {

  it("child.text with stream") {

    val bus = new EventBus[String]

    val el = div(
      "Hello",
      child.text <-- bus
    )

    mount(el)

    expectNode(
      div of(
        "Hello",
        sentinel
      )
    )

    // --

    bus.emit("a")

    expectNode(
      div of(
        "Hello",
        "a"
      )
    )

    // --

    bus.emit("b")

    expectNode(
      div of(
        "Hello",
        "b"
      )
    )
  }

  it("multiple child.text with stream") {

    val bus1 = new EventBus[String]
    val bus2 = new EventBus[String]

    val el = div(
      "Hello",
      child.text <-- bus1,
      child.text <-- bus2,
    )

    mount(el)

    expectNode(
      div of(
        "Hello",
        sentinel,
        sentinel
      )
    )

    // --

    bus1.emit("a")

    expectNode(
      div of(
        "Hello",
        "a",
        sentinel
      )
    )

    // --

    bus1.emit("b")

    expectNode(
      div of(
        "Hello",
        "b",
        sentinel
      )
    )

    // --

    bus2.emit("1")

    expectNode(
      div of(
        "Hello",
        "b",
        "1"
      )
    )

    // --

    bus2.emit("2")

    expectNode(
      div of(
        "Hello",
        "b",
        "2"
      )
    )

    // --

    EventBus.emit(
      bus2 -> "3",
      bus1 -> "c"
    )

    expectNode(
      div of(
        "Hello",
        "c",
        "3"
      )
    )
  }
}

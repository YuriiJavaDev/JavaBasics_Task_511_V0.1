## Action and action handling in Swing.

### In Swing, almost every user interaction is an event:

- pressing a button
- selecting a menu item
- pressing Enter
- hotkey

To handle such actions, the following mechanism is used:

```java
ActionListener
```

and a more advanced mechanism:

```java
Action / AbstractAction
```

---

**Basic Idea**

When the user presses a button:

```
user → event → handler → code
```

For example:

```java
button clicked
↓
actionPerformed()
↓
code is executed
```

---

### Method 1 — **ActionListener**

This is the basic and most popular method.

---

#### How to connect

```java
button.addActionListener(...)
```

---

**Full example**

```java
JButton button = new JButton("Click");

button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked");
    }
});
```

---

👉 **What happens**

When the user clicks the button:

1. Swing creates an `ActionEvent`
2. Calls:

```java
actionPerformed(...)
```

3. The code inside the method is executed

---

👉 **What is an ActionEvent**

```java
ActionEvent e
```

The event object.

Contains information:

- who triggered the event
- when
- what command was executed

---

👉 **Frequently Used Methods**

#### Get the event source

```java
e.getSource()
```

Example:

```java
JButton btn = (JButton)e.getSource();
System.out.println(btn);
```

Example output:

```java 
javax.swing.JButton[,362,5,62x26,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBor derUIResource@6517ec7f,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelect edIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Click,defaultCapable=true] 

``` 

👉 **Information about Button**

```
javax.swing.JButton[,362,5,62x26,...,text=Click,...]
```

**This is** `toString()` for `JButton`

Swing shows:

- Component type
- Coordinates
- Size
- Settings
- Text
- State

---

**🔥What can actually be used here**

**✅ 1. Component type**

```
javax.swing.JButton
```

👉 This means the event source is a button

---

**✅ 2. Button text**

```
text=Click
```

👉 This is the most useful field

Can be accessed like this:

```java
btn.getText();
```

---

**✅ 3. Button Size**

```
62x26
```

👉 Width × Height

Rarely used, but useful for layout debugging

---

**✅ 4. Screen Position**

```
[,362,5,...]
```

👉 x = 362, y = 5

These are the coordinates within the container.

---

**✅ 5. Margin**

```
margin=InsetsUIResource[top=2,left=14,bottom=2,right=14]
```

👉 Button Padding

---

**✅ 6. Button State**

For example:

- enabled/disabled
- focus
- rollover

---

**🧠 What NOT to do Use**

❌ Parse this string manually

❌ Try to parse `toString()`

👉 This is **debug information**, not an API

---

#### Get the command text

```java
e.getActionCommand()
```

Typically returns the button text.

---

**Example with changing color**

```java
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setBackground(Color.RED);
    }
});
```

---
**Disadvantages of ActionListener**

For a large application:

- a lot of identical code
- difficult to reuse actions
- the same logic is duplicated

---

**Duplicate Problem**

For example:

- Save button
- Save menu item
- Ctrl + S

They should all perform the same action.

With ActionListener, you'll have to write handlers separately.

---

**Solution: Swing offers a more powerful system: Action**

---

## Method 2 - Action

Action is an **action object** of an interface.

For example:

```
SaveAction
DeleteAction
OpenAction
ChangeColorAction
```

---

**The main idea of Action**

Separate:

- interface
- from the action logic

---

### AbstractAction

In practice, `AbstractAction` is used - it is a ready-made class implementing the `Action` interface.

---

**AbstractAction Example**

```java
static class MyAction extends AbstractAction {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action executed");
    }
}
```

---

**Connecting to a Button**

```java
JButton button = new JButton(new MyAction());
```

---

**What's Happening**

```
JButton
   ↓
Action
   ↓
actionPerformed()
```

---

**Why Action is More Powerful**

A single action can be used in multiple places.

---

**Example**

```java
//an action object is created (logic: "save file")
Action saveAction = new SaveAction();

//the button is created NOT with text, but with an action
JButton button = new JButton(saveAction);
//the menu item receives the SAME action
menuItem.setAction(saveAction);
```

---

Now:

- Save button
- Save menu

use the same code.

**🔥 Without Action, we'd be writing the same thing twice:

```java
button.addActionListener(...)
menuItem.addActionListener(...)
```

❌ Duplicated code

---

**Action and hotkeys**

Action works great with:

- InputMap
- ActionMap
- Key Bindings

---

**Example**

```java
actionMap.put("save", saveAction);
```

Now:

- Ctrl + S
- button
- menu

can trigger a single action.

---

### Additional Action Capabilities

**Action Unifies Everything**

`Action` stores:

- what to do (logic)
- how it looks (UI)

**🔹 putValue() —** 👉 this is like “setting an action property”

```java
putValue(KEY,VALUE);
```

---

#### Button Text

```java
putValue(Action.NAME, "Save");
```

👉 this is the text the user will see.

📌 equivalent:

```java
button.setText("Save");
```

---

#### Tooltip (tooltip)

```java
putValue(Action.SHORT_DESCRIPTION, "Save file");
```

👉 Mouseover text

📌 Equivalent:

```java
button.setToolTipText("Save file");
```

---

#### Icon

```java
Icon icon = new ImageIcon(getClass().getResource("/icons/save.png"));
//...
putValue(Action.SMALL_ICON, icon);
```

👉 Image on a button or in a menu

- More about `new ImageIcon(getClass().getResource("/icons/save.png"));`

**What's going on here**

👉 We load an image from the project resources and turn it into a Swing icon.

---

```java
getClass()
```

👉 Returns the class we're currently in.

---

```java
getClass().getResource("/icons/save.png")
```

👉 Looks for a file within the project (in the classpath)

---

```java
"/icons/save.png"
```

👉 means:

> search from the resource root
>

---

**🔥 Where Java looks for the file**

```
src/
└── icons/
└── save.png
```

- **🧠 How to change the icon size**

```java
ImageIcon icon = new ImageIcon(getClass().getResource("/icons/save.png"));

Image img = icon.getImage();
Image scaled = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

Icon smallIcon = new ImageIcon(scaled);
```

👉 then:

```java
putValue(Action.SMALL_ICON, smallIcon);
```

- **🧠 How to swap text and icon**

In Swing, this is done not by "manually rearranging the icon," but by adjusting the **text position relative to the icon**.

---

**✔ Main Method**

```java
button.setHorizontalTextPosition(SwingConstants.RIGHT);
button.setHorizontalAlignment(SwingConstants.LEFT);
```

---

**🔥 Most common option (icon on the right, text on the left)**

```java
button.setHorizontalTextPosition(SwingConstants.LEFT);
button.setHorizontalAlignment(SwingConstants.RIGHT);
```

---

**💡 What does this mean**

**📌 horizontalTextPosition**

👉 Where is the text relative to the icon?

- LEFT → text to the left of the icon
- RIGHT → text to the right of the icon
- CENTER → above (rare)

---

**📌 horizontalAlignment**

👉 How all content is aligned within the button

---

🧠 Example

Before:

```
[ 💾 Save ]
```

Now:

```
Save 💾
```

---

📌 Also works for **JMenuItem**

```java
menuItem.setHorizontalTextPosition(SwingConstants.LEFT);
```

---

📌 Equivalent:

```java
button.setIcon(icon);
```

---

**Example of a full-fledged Action**

```java
static class SaveAction extends AbstractAction {
    
    public SaveAction() {
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save file");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Saving...");
    }
}
```

---

Usage:

```java
JButton button = new JButton(new SaveAction());
```

---

**What the button automatically receives**

- text
- tooltip
- enabled state
- action handler

---

**Important advantage of Action**

You can **disable** all associated elements at once.

This means that the Action itself becomes "disabled."

The connection remains: the button, menu, and hotkeys are still bound to this Action, but it simply cannot be executed.

Result:

- the button becomes inactive (grayed out)
- the menu item is also inactive
- the hotkey does not work

In other words, the Action is not deleted or unbound; it is simply temporarily disabled.

---

**Example**

```java
saveAction.setEnabled(false);
```

And the following will be automatically disabled:

- button
- menu
- hotkey

---

## Lambda Expressions

Since `ActionListener` is a functional interface:

it can be written more concisely.

---

**Instead**

```java
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
});
```

---

**They write**

```java
button.addActionListener(e -> {
    System.out.println("Clicked");
});
```

---

**When to use ActionListener**

Suitable for:

- small projects
- educational tasks
- simple buttons

---

**When to use Action**

Suitable for:

- large applications
- menu
- toolbar
- hotkeys
- reusable actions

---

# Comparison of Methods

| Method | Where to use | Level |
| --- | --- | --- |
| addActionListener | simple GUIs | basic |
| lambda ActionListener | modern simple code | most popular |
| Action / AbstractAction | large Swing applications | advanced |

---

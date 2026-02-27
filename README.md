# 🎮 Warrior Adventure (Giochino)

A 2D top-down Java game built as a personal engineering project to practice **game architecture, event systems, collision logic, UI state management, and iterative debugging**.

> Designed and developed by [Enrico Rossi](https://github.com/enrico0rossi1)

---

## 🚀 Why this project matters

This is not just a “school assignment” game — it’s a hands-on software engineering sandbox where I implemented and improved:

- Object-oriented game structure in Java
- Entity management and collision detection
- Event-driven mechanics (traps, healing, teleport)
- Game states (menu, gameplay, pause, game over)
- HUD updates (health hearts, key counters, notifications)
- Audio integration (background music + SFX)
- Feature iteration with bug tracking and refactoring

---

## 🕹️ Gameplay Highlights

- Explore tile-based maps
- Collect items and keys
- Unlock movement upgrades (run after collecting shoes)
- Avoid hazards and manage health
- Reach the final treasure to complete the run

---

## 🧱 Tech Stack

- **Language:** Java
- **Paradigm:** OOP
- **Architecture:** modular packages (`entities`, `gameworld`, `audio`, game core)
- **Assets:** sprite-based rendering + audio effects

---

## 🧭 Controls

- **W / A / S / D** (or arrows): Move  
- **P**: Attack  
- **O**: Run (after unlocking shoes)  
- **M**: Pause / Resume  

---

## 📂 Project Structure

```text
src/
  entities/          # Player and entity logic
  gameworld/         # World/map logic
  warrioradventure/  # Main game loop and core systems
  audio/             # Music and sound effects
```

---

## 🛠️ Run Locally

### Option A — IDE (recommended)
1. Clone the repository
   ```bash
   git clone https://github.com/enrico0rossi1/giochino.git
   ```
2. Open in IntelliJ / Eclipse / VS Code
3. Run the main class (`warrioradventure.Main`, or your configured entry point)

### Option B — Command line (example)
```bash
javac -d out $(find src -name "*.java")
java -cp out warrioradventure.Main
```

---

## 📈 Engineering Roadmap

- [ ] Improve attack animation state transitions
- [ ] Add input debouncing for pause/action keys
- [ ] Expand and polish DarkWoods map
- [ ] Improve enemy variety and balancing
- [ ] Add save/load support
- [ ] Add lightweight tests for core mechanics

---

## 🧠 What I learned

This project strengthened my practical skills in:

- Breaking complex behavior into modular systems
- Debugging real-time interactions in a game loop
- Refactoring while keeping gameplay stable
- Shipping features incrementally and documenting known issues

---

## 👨‍💻 About Me

I’m an **AI Engineering student** building practical software projects to improve both system design and implementation quality.

If you’re a recruiter or engineer reviewing this repo, feedback is welcome.  
GitHub: [@enrico0rossi1](https://github.com/enrico0rossi1)

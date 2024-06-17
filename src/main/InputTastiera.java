package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputTastiera implements KeyListener {

    public boolean w, a, s, d, p, o, m, f, enter;
    Pannello gp;
    
    // debug
    public boolean z = false;

    public InputTastiera(Pannello gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent t) {
        // This method is required by the KeyListener interface but not used
    }

    @Override 
    public void keyPressed(KeyEvent e) {
        int premuto = e.getKeyCode();

        if (gp.gameState == gp.titleState) {
            handleTitleState(premuto);
        } else if (gp.gameState == gp.playState) {
            handlePlayState(premuto);
        } else if (gp.gameState == gp.pauseState) {
            handlePauseState(premuto);
        } else if (gp.gameState == gp.dialogueState) {
            handleDialogueState(premuto);
        } else if (gp.gameState == gp.optionsState) {
            handleOptionsState(premuto);
        } else if (gp.gameState == gp.gameOver) {
            handleGameOverState(premuto);
        } else if (gp.gameState == gp.endGame) {
            handleEndGameState(premuto);
        }
    }

    private void handleTitleState(int premuto) {
        if (premuto == KeyEvent.VK_W) {
            gp.stopMusic(4);
            gp.playMusic(0);
            gp.eventHandler.currentMapIndex = 0;
            gp.gameState = gp.playState;
        }
    }

    private void handlePlayState(int premuto) {
        if (premuto == KeyEvent.VK_M) {
            gp.gameState = gp.pauseState;
            gp.playSFX(11);
            gp.stopMusic(2);
            gp.playMusic(5);
        } else {
            if (premuto == KeyEvent.VK_W) {
                w = true;
            } else if (premuto == KeyEvent.VK_A) {
                a = true;
            } else if (premuto == KeyEvent.VK_S) {
                s = true;
            } else if (premuto == KeyEvent.VK_D) {
                d = true;
            } else if (premuto == KeyEvent.VK_P) {
                p = true;
            } else if (premuto == KeyEvent.VK_O) {
                o = true;
            }
        }
    }

    private void handlePauseState(int premuto) {
        if (premuto == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
            gp.playSFX(10);
            gp.stopMusic(5);
            gp.playMusic(gp.eventHandler.currentMapIndex);
        } else if (premuto == KeyEvent.VK_O) {
            gp.gameState = gp.optionsState;
            gp.playSFX(11);
        }
    }

    private void handleDialogueState(int premuto) {
        if (premuto == KeyEvent.VK_D) {
            if (gp.ui.dialogueChoice == 1) {
                gp.ui.dialogueChoice++;
            }
        } else if (premuto == KeyEvent.VK_A) {
            if (gp.ui.dialogueChoice == 2) {
                gp.ui.dialogueChoice--;
            }
        } else if (premuto == KeyEvent.VK_ENTER) {
            handleDialogueEnter();
        }
    }

    private void handleDialogueEnter() {
        if (gp.fullScreenOn && "Per ridurre le dimensioni dello schermo riavviare il gioco.".equals(gp.ui.currentDialogue)) {
            gp.playSFX(10);
            gp.gameState = gp.optionsState;
        } else {
            gp.gameState = gp.playState;
            if (gp.ui.dialogueChoice == 2) {
                gp.eventHandler.teleport(gp.eventHandler.nextMap);
                gp.playSFX(7);
            } else if (gp.ui.dialogueChoice == 1) {
                gp.playSFX(10);
            }
        }
    }

    private void handleOptionsState(int premuto) {
        int maxCommandNum = 5;

        if (premuto == KeyEvent.VK_W) {
            if (gp.ui.commandNum > 0) {
                gp.playSFX(11);
                gp.ui.commandNum--;
            }
        } else if (premuto == KeyEvent.VK_S) {
            if (gp.ui.subState == 0 && gp.ui.commandNum < maxCommandNum) {
                gp.playSFX(11);
                gp.ui.commandNum++;
            } else if (gp.ui.subState == 2 && gp.ui.commandNum < 1) {
                gp.ui.commandNum++;
            }
        } else if (premuto == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                } else if (gp.ui.commandNum == 2 && gp.sfx.volumeScale > 0) {
                    gp.sfx.volumeScale--;
                    gp.playSFX(11);
                }
            }
        } else if (premuto == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                } else if (gp.ui.commandNum == 2 && gp.sfx.volumeScale < 5) {
                    gp.sfx.volumeScale++;
                    gp.playSFX(11);
                }
            }
        } else if (premuto == KeyEvent.VK_ENTER) {
            handleOptionsEnter(maxCommandNum);
        }
    }

    private void handleOptionsEnter(int maxCommandNum) {
        if (gp.ui.subState == 0) {
            switch (gp.ui.commandNum) {
                case 0:
                    gp.playSFX(9);
                    if (gp.fullScreenOn) {
                        gp.ui.currentDialogue = "Per ridurre le dimensioni dello schermo riavviare il gioco.";
                        gp.ui.dialogueChoice1 = "";
                        gp.ui.dialogueChoice2 = "";
                        gp.ui.dialogueChoice = 3;
                        gp.gameState = gp.dialogueState;
                    } else {
                        gp.fullScreenOn = true;
                    }
                    break;
                case 3:
                    gp.ui.subState = 1;
                    gp.playSFX(9);
                    break;
                case 4:
                    gp.playSFX(9);
                    gp.ui.subState = 2;
                    gp.ui.commandNum = 1;
                    break;
                case 5:
                    gp.playSFX(10);
                    gp.gameState = gp.pauseState;
                    break;
            }
        } else if (gp.ui.subState == 1) {
            gp.ui.subState = 0;
            gp.playSFX(10);
        } else if (gp.ui.subState == 2) {
            maxCommandNum = 1;
            if (gp.ui.commandNum == 1) {
                gp.playSFX(10);
                gp.ui.subState = 0;
            } else if (gp.ui.commandNum == 0) {
                gp.gameState = gp.titleState;
                gp.ui.subState = 0;
                gp.stopMusic(5);
                gp.playMusic(4);
            }
        }
    }

    private void handleGameOverState(int premuto) {
        if (premuto == KeyEvent.VK_ENTER) {
            if (gp.ui.gameOverChoice == 1) {
                gp.gameState = gp.titleState;
                gp.playMusic(4);
                gp.retry();
            } else if (gp.ui.gameOverChoice == 0) {
                gp.playMusic(gp.eventHandler.startingWoodsMap);
                gp.retry();
                gp.gameState = gp.playState;
            }
        } else if (premuto == KeyEvent.VK_D) {
            if (gp.ui.gameOverChoice == 0) {
                gp.playSFX(11);
                gp.ui.gameOverChoice = 1;
            }
        } else if (premuto == KeyEvent.VK_A) {
            if (gp.ui.gameOverChoice == 1) {
                gp.playSFX(11);
                gp.ui.gameOverChoice = 0;
            }
        }
    }

    private void handleEndGameState(int premuto) {
        if (premuto == KeyEvent.VK_ENTER) {
            gp.gameState = gp.titleState;
            gp.stopMusic(12);
            gp.playMusic(4);
            gp.retry();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int rilasciato = e.getKeyCode();

        if (rilasciato == KeyEvent.VK_W) {
            w = false;
        } else if (rilasciato == KeyEvent.VK_A) {
            a = false;
        } else if (rilasciato == KeyEvent.VK_S) {
            s = false;
        } else if (rilasciato == KeyEvent.VK_D) {
            d = false;
        } else if (rilasciato == KeyEvent.VK_P) {
            p = false;
        } else if (rilasciato == KeyEvent.VK_O) {
            o = false;
        }
    }
}

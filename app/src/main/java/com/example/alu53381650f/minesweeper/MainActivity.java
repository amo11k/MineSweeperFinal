package com.example.alu53381650f.minesweeper;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;
    public Boton[][] buttons;
    private GridLayout tablero;
    public int[][] numBombs;
    private int score;
    TextView scoreLabel;
    ImageButton icono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icono = (ImageButton) findViewById(R.id.imageButton);
        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        start();


    }

    public void crearTablero(GridLayout tablero) {

        buttons = new Boton[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                buttons[i][j] = new Boton(getApplicationContext(), i, j);
                buttons[i][j].setLayoutParams(new GridLayout.LayoutParams());
                buttons[i][j].setPadding(0, 0, 0, 0);
                tablero.addView(buttons[i][j], new GridLayout.LayoutParams(tablero.spec(i), tablero.spec(j)));
            }
        }
    }

    public void ponerBombas() {
        int contador_bombas = 0;
        while (contador_bombas <= 10) {//(NUM_COLS * NUM_ROWS / 8)) {
            int i = (int) (Math.random() * NUM_ROWS - 1);
            int j = (int) (Math.random() * NUM_COLS - 1);
            buttons[i][j].setState(Estados.BOMBA);
            contador_bombas++;
        }
        fullNumBombs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getNumBombs(Boton b) {
        int proxibombs = 0;
        int x = b.getPosX();
        int y = b.getPosY();
        //Esq Izq
        if ((x - 1 >= 0) && (y - 1 >= 0))
            //ESQ SUP IZ
            if (buttons[x - 1][y - 1].getState() == Estados.BOMBA)
                proxibombs++;
        if (y - 1 >= 0)
            //MID SUP
            if (buttons[x][y - 1].getState() == Estados.BOMBA)
                proxibombs++;
        if ((x + 1 < NUM_ROWS) && (y - 1 >= 0))
            //ESQ SUP DRCHA
            if (buttons[x + 1][y - 1].getState() == Estados.BOMBA)
                proxibombs++;
        if (x - 1 >= 0)
            //MID IZQ
            if (buttons[x - 1][y].getState() == Estados.BOMBA)
                proxibombs++;
        if (x + 1 < NUM_ROWS)
            // MID DRCH
            if (buttons[x + 1][y].getState() == Estados.BOMBA)
                proxibombs++;
        if (y + 1 < NUM_COLS)
            //MID BOTTOM
            if (buttons[x][y + 1].getState() == Estados.BOMBA)
                proxibombs++;
        if ((x - 1 >= 0) && (y + 1 < NUM_COLS))
            //ESQ IZQ INF
            if (buttons[x - 1][y + 1].getState() == Estados.BOMBA)
                proxibombs++;
        if ((x + 1 < NUM_ROWS) && (y + 1 < NUM_COLS))
            //ESQ INF DRCHA
            if (buttons[x + 1][y + 1].getState() == Estados.BOMBA)
                proxibombs++;

        return proxibombs;
    }

    public void fullNumBombs() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                numBombs[i][j] = getNumBombs(buttons[i][j]);
            }
        }
    }

    public void getPicNum(Boton b) {
        incrementScore();
        int x = b.getPosX();
        int y = b.getPosY();
        switch (numBombs[x][y]) {
            case 0:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c0));
                b.setState(Estados.PULSADO);
                break;
            case 1:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c1));
                b.setState(Estados.PULSADO);
                break;
            case 2:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c2));
                b.setState(Estados.PULSADO);
                break;
            case 3:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c3));
                b.setState(Estados.PULSADO);
                break;
            case 4:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c4));
                b.setState(Estados.PULSADO);
                break;
            case 5:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c5));
                b.setState(Estados.PULSADO);
                break;
            case 6:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c6));
                b.setState(Estados.PULSADO);
                break;
            case 7:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c7));
                b.setState(Estados.PULSADO);
                break;
            case 8:
                b.setImageDrawable(getResources().getDrawable(R.drawable.c8));
                b.setState(Estados.PULSADO);
                break;
            default:
                break;
        }
    }

    public void incrementScore() {
        score++;
        scoreLabel.setText(String.valueOf(score));
    }

    public void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("You are dead. Score: " + score + ". Do u wanna replay it?");
        alertDialogBuilder.setIcon(R.drawable.mine);

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                start();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void start() {
        score = 0;
        scoreLabel = (TextView) findViewById(R.id.score);
        scoreLabel.setText(String.valueOf(score));
        crearTablero(tablero = (GridLayout) findViewById(R.id.tablero));
        numBombs = new int[NUM_ROWS][NUM_COLS];
        ponerBombas();

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boton b = (Boton) v;
                        int x = b.getPosX();
                        int y = b.getPosY();
                        if (b.getState() != Estados.FLAG) {
                            if (b.getState() == Estados.BOMBA) {
                                b.setImageDrawable(getResources().getDrawable(R.drawable.mine_wrong));
                                gameOver();
                            } else if (numBombs[x][y] == 0) {
                                for (int i = x; i < NUM_ROWS; i++) {
                                    for (int j = y; j < NUM_COLS; j++) {
                                        if ((numBombs[i][j] <= 1) && (buttons[i][j].getState() != Estados.BOMBA) && (buttons[i][j].getState() != Estados.FLAG)) {
                                            getPicNum(buttons[i][j]);
                                            //buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.c0));
                                            buttons[i][j].setState(Estados.PULSADO);
                                        }
                                    }
                                }
                                for (int i = x; i > 0; i--) {
                                    for (int j = y; j > 0; j--) {
                                        if ((numBombs[i][j] <= 1) && (buttons[i][j].getState() != Estados.BOMBA) && (buttons[i][j].getState() != Estados.FLAG)) {
                                            getPicNum(buttons[i][j]);
                                            //buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.c0));
                                            buttons[i][j].setState(Estados.PULSADO);
                                        }
                                    }
                                }

                            } else {
                                getPicNum(b);
                            }
                        }

                    }
                });
                buttons[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Boton b = (Boton) v;

                        if ((b.getState() == Estados.NUEVO) || (b.getState() == Estados.BOMBA)) {
                            b.setImageDrawable(getResources().getDrawable(R.drawable.flag));
                            b.setState(Estados.FLAG);
                        } else if (b.getState() == Estados.FLAG) {
                            getPicNum(b);

                        }
                        return true;
                    }
                });
            }
        }
    }
}

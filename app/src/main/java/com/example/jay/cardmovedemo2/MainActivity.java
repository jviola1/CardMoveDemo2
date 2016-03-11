package com.example.jay.cardmovedemo2;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Rect screen = new Rect(10,10,750,900);

    Pile pile1, pile2, pile3, pile4;

    Pile[] pileList = new Pile[4];

    int numberOfCards = 8;
    Card[] cardList = new Card[numberOfCards];

    ArrayList<Card> tempList = new ArrayList<Card>();

    int lastIndexOfPile = -1;

    public boolean onTouchEvent(MotionEvent e){

        int action = MotionEventCompat.getActionMasked(e);

        switch(action){
            case MotionEvent.ACTION_DOWN:

                Log.d("Message:","touchPoint:"+String.valueOf((int)e.getRawX())+" "+String.valueOf((int)e.getRawY()-160));
                Log.d("Message:","templist:"+String.valueOf(tempList.size()));
                Log.d("Message:","Pile1:"+String.valueOf(pile1.cardList.size()));
                Log.d("Message:","Pile2:"+String.valueOf(pile2.cardList.size()));
                Log.d("Message:","Pile3:"+String.valueOf(pile3.cardList.size()));
                Log.d("Message:","Pile4:"+String.valueOf(pile4.cardList.size()));


                for(int i = 0;i<pileList.length;i++){

                    if(pileList[i].area.contains((int)e.getRawX(),(int)e.getRawY()-160)){

                        lastIndexOfPile = i;
                        pileList[i].addCardsSelectedToList(tempList, (int) e.getRawX(), (int) e.getRawY() - 160);
                        pileList[i].removeCardFromCardToLast(tempList.get(0));

                        break;
                    }

                }

                if(tempList.size()!=0) {
                    for (int i = 0; i < tempList.size();i++) {
                        tempList.get(i).setLocation((int) e.getRawX()-50,(int) e.getRawY() - 160-50+40*i);
                    }
                }

                return true;

            case MotionEvent.ACTION_MOVE:

                if(screen.contains((int)e.getRawX(),(int)e.getRawY()-160)) {
                    if (tempList.size() != 0) {
                        for (int i = 0; i < tempList.size(); i++) {
                            tempList.get(i).setLocation((int) e.getRawX() - 50, (int) e.getRawY() - 160 - 50 + 40 * i);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                tempList.get(i).img.setElevation(100 + i);
                            }
                        }
                    }

                }
                else{
                    if(tempList.size()!=0){
                        pileList[lastIndexOfPile].addCardFromList(tempList);
                        tempList.clear();
                    }
                }

                return true;

            case  MotionEvent.ACTION_UP:

                if(screen.contains((int)e.getRawY(),(int)e.getRawY())) {

                    if (tempList.size() != 0) {
                        for (int i = 0; i < pileList.length; i++) {
                            if (pileList[i].area.contains((int) e.getRawX(), (int) e.getRawY() - 160)) {

                                if (pileList[i].addCardFromList(tempList)) {
                                    tempList.clear();
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }
                        if (tempList.size() != 0) {
                            pileList[lastIndexOfPile].addCardFromList(tempList);
                            tempList.clear();
                        }
                    }

                }
                else {
                    if(tempList.size()!=0){
                        pileList[lastIndexOfPile].addCardFromList(tempList);
                        tempList.clear();
                    }
                }

                return true;


            default:
                return super.onTouchEvent(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView pileImg1 = (ImageView)findViewById(R.id.pile1);
        final ImageView pileImg2 = (ImageView)findViewById(R.id.pile2);
        final ImageView pileImg3 = (ImageView)findViewById(R.id.pile3);
        final ImageView pileImg4 = (ImageView)findViewById(R.id.pile4);
        final ImageView cardImg1 = (ImageView)findViewById(R.id.card1);
        final ImageView cardImg2 = (ImageView)findViewById(R.id.card2);
        final ImageView cardImg3 = (ImageView)findViewById(R.id.card3);
        final ImageView cardImg4 = (ImageView)findViewById(R.id.card4);
        final ImageView cardImg5 = (ImageView)findViewById(R.id.card5);
        final ImageView cardImg6 = (ImageView)findViewById(R.id.card6);
        final ImageView cardImg7 = (ImageView)findViewById(R.id.card7);
        final ImageView cardImg8 = (ImageView)findViewById(R.id.card8);

        ViewTreeObserver vto1 = pileImg1.getViewTreeObserver();
        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pileImg1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pile1 = new Pile(new Point(pileImg1.getLeft(),pileImg1.getTop()));

                pileList[0] = pile1;
            }
        });

        ViewTreeObserver vto2 = pileImg2.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pileImg2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pile2 = new Pile(new Point(pileImg2.getLeft(),pileImg2.getTop()));

                pileList[1] = pile2;
            }
        });

        ViewTreeObserver vto3 = pileImg3.getViewTreeObserver();
        vto3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pileImg3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pile3 = new Pile(new Point(pileImg3.getLeft(),pileImg3.getTop()));

                pileList[2] = pile3;
            }
        });

        ViewTreeObserver vto4 = pileImg4.getViewTreeObserver();
        vto4.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pileImg4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pile4 = new Pile(new Point(pileImg4.getLeft(),pileImg4.getTop()));

                pileList[3] = pile4;
            }
        });


        ViewTreeObserver vto5 = cardImg1.getViewTreeObserver();
        vto5.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card1 = new Card(pile1,cardImg1);
                cardList[0] = card1;
            }
        });


        ViewTreeObserver vto6 = cardImg2.getViewTreeObserver();
        vto6.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card2 = new Card(pile1,cardImg2);
                cardList[1] = card2;
            }
        });

        ViewTreeObserver vto7 = cardImg3.getViewTreeObserver();
        vto7.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card3 = new Card(pile2,cardImg3);
                cardList[2] = card3;
            }
        });

        ViewTreeObserver vto8 = cardImg4.getViewTreeObserver();
        vto8.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card4 = new Card(pile4,cardImg4);
                cardList[3] = card4;
            }
        });

        ViewTreeObserver vto9 = cardImg5.getViewTreeObserver();
        vto9.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg5.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card5 = new Card(pile1,cardImg5);
                cardList[4] = card5;
            }
        });

        ViewTreeObserver vto10 = cardImg6.getViewTreeObserver();
        vto10.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg6.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card6 = new Card(pile3,cardImg6);
                cardList[5] = card6;
            }
        });

        ViewTreeObserver vto11 = cardImg7.getViewTreeObserver();
        vto11.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg7.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card7 = new Card(pile1,cardImg7);
                cardList[6] = card7;
            }
        });

        ViewTreeObserver vto12 = cardImg8.getViewTreeObserver();
        vto12.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardImg8.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Card card8 = new Card(pile4,cardImg8);
                cardList[7] = card8;
            }
        });

    }
}

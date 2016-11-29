package com.example.adolfo.firmas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RelativeLayout layout1;
    ProgressBar pbGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        layout1= (RelativeLayout) findViewById(R.id.content_main);
        Pintar pintar = new Pintar(this);
        layout1.addView(pintar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    class Pintar extends View{
        float x = 50;
        float y = 50;
        String accion = "accion";
        Path path = new Path();




        public Pintar(Context context){
            super(context);
        }

        public void onDraw (Canvas canvas) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setColor(Color.BLUE);
            canvas.drawPath(path, paint);


            if(accion=="down"){
                path.moveTo(x,y);
            }
            if(accion=="move"){
                path.lineTo(x,y);
            }

        }

        public boolean onTouchEvent(MotionEvent e){
            x = e.getX();
            y = e.getY();

            if(e.getAction() == MotionEvent.ACTION_DOWN){
                accion = "down";
            }
            if(e.getAction() == MotionEvent.ACTION_MOVE){
                accion = "move";
            }
            invalidate();
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        if (id == R.id.borrar) {
            Pintar pintar = new Pintar(this);
            layout1.removeAllViews();
            layout1.addView(pintar);
            return true;
        }
        if (id == R.id.guardar) {
            pbGuardar= (ProgressBar) findViewById(R.id.barra);
            pbGuardar.setProgress(0);
            pbGuardar.setVisibility(View.VISIBLE);
            for(int i=0;i<=100;i++){
                pbGuardar.setProgress(i);
                try{
                    Thread.sleep(20);
                }catch (Exception e){}
            }if(pbGuardar.getProgress()==100){
                pbGuardar.setVisibility(View.GONE);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.panel_dibujo) {
            // Handle the camera action
        } else if (id == R.id.cambiarRojo) {
            layout1.removeAllViews();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Pintar pintar = new Pintar(this);
                layout1.setBackgroundColor(Color.RED);
                layout1.addView(pintar);
            }

        } else if (id == R.id.cambiarBlanco) {
            layout1.removeAllViews();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Pintar pintar = new Pintar(this);
                layout1.setBackgroundColor(Color.WHITE);
                layout1.addView(pintar);
            }

        } else if (id == R.id.cambiarGris) {
                layout1.removeAllViews();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    Pintar pintar = new Pintar(this);
                    layout1.setBackgroundColor(Color.GRAY);
                    layout1.addView(pintar);
                }
        }else if (id == R.id.cambiarVerde) {
            layout1.removeAllViews();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Pintar pintar = new Pintar(this);
                layout1.setBackgroundColor(Color.GREEN);
                layout1.addView(pintar);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.dmb.repasoexamenpmdm2ev;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dmb.repasoexamenpmdm2ev.Fragments.AddProductFragment;
import com.dmb.repasoexamenpmdm2ev.Fragments.ListProductsFragment;
import com.dmb.repasoexamenpmdm2ev.Models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AddProductFragment.OnFragmentInteractionListener,
        ListProductsFragment.OnFragmentInteractionListener{

    FragmentManager fm;
    FragmentTransaction ft;
    TextView tvMainTitle;

    boolean showingFragment;

    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvMainTitle = findViewById(R.id.tvMainTitle);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        android.app.FragmentManager fm;
        android.app.FragmentTransaction ft;
        fm = getFragmentManager();
        ft = fm.beginTransaction();

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(showingFragment){
            ft.remove(getFragmentManager().findFragmentById(R.id.mainFragment));
            ft.commit();
            showingFragment = false;
            tvMainTitle.setVisibility(View.VISIBLE);
        }else{
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
        if (id == R.id.actionSettings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAddProducts) {
            tvMainTitle.setVisibility(View.GONE);
            fm = getSupportFragmentManager();
            fm.popBackStack();
            ft = fm.beginTransaction();
            ft.add(R.id.mainFragment, AddProductFragment.newInstance("",""));
            ft.addToBackStack(null);
            ft.commit();
            Toast.makeText(this, "Añadir Producto", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.navListProducts){
            tvMainTitle.setVisibility(View.GONE);
            fm = getSupportFragmentManager();
            fm.popBackStack();
            ft = fm.beginTransaction();
            ft.add(R.id.mainFragment, ListProductsFragment.newInstance("",""));
            ft.addToBackStack(null);
            ft.commit();
            Toast.makeText(this, "Listado de Productos", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.navSettings) {

        } else if (id == R.id.navCopyright) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public Product getProduct(int position) {
        return this.products.get(position);
    }

    @Override
    public void addProduct(Product product) {
        this.products.add(product);
    }
}

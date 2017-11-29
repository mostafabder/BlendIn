package blendin.blendin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {

    private ArrayList<String> mTitles = new ArrayList<>();
    Toolbar toolbar;
    adapter menuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DuoMenuView duoMenuView = (DuoMenuView) findViewById(R.id.menuView);
        mTitles=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));
         menuAdapter = new adapter(mTitles);
        duoMenuView.setAdapter(menuAdapter);

        toolbar=(Toolbar)findViewById(R.id.toolbar) ;
        DuoDrawerLayout drawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onFooterClicked() {
        Toast.makeText(MainActivity.this,"logout",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHeaderClicked() {

    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        toolbar.setTitle(mTitles.get(position));
        menuAdapter.setViewSelected(position, true);
        switch (position)
        {
            case 0:
                Toast.makeText(MainActivity.this,"profile",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(MainActivity.this,"Hangouts",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this,"Newsfeed",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this,"My squad",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(MainActivity.this,"Settings",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

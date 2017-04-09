package cz.cra.redcute.crahackredcute;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

import cz.cra.redcute.crahackredcute.database.DeviceDbAdapter;
import cz.cra.redcute.crahackredcute.model.Device;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {


    ArrayList<Device> list;
    public static MainAdapter adapter;

    public MainActivityFragment() {
        list = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launchDeviceDetailActivity(MainActivity.FragmentToLaunch.VIEW, position);
    }

    private void launchDeviceDetailActivity(MainActivity.FragmentToLaunch view, int position) {
        Device device = (Device) getListAdapter().getItem(position);
        Intent intent = new Intent(getContext(), DeviceDetailActivity.class);

        intent.putExtra(Device.NAME, device.getName());
        intent.putExtra(Device.DEVEUI, device.getDevEUI());

        startActivity(intent);

    }


    @Override
    public void onResume() {
        super.onResume();

        DeviceDbAdapter deviceDbAdapter = new DeviceDbAdapter(getActivity().getBaseContext());
        deviceDbAdapter.open();
        list = deviceDbAdapter.getAllBoxes();
        deviceDbAdapter.close();



        adapter = new MainAdapter(getActivity().getApplicationContext(), list);

        setListAdapter(adapter);

        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;
        Device device = (Device) getListAdapter().getItem(rowPosition);

        switch (item.getItemId()){

            case R.id.delete:
                DeviceDbAdapter dbAdapter = new DeviceDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();
                dbAdapter.deleteBox(device.getDeviceId());

                list.clear();
                list.addAll(dbAdapter.getAllBoxes());

                adapter.notifyDataSetChanged();
                dbAdapter.close();

        }

        return super.onContextItemSelected(item);

    }
}

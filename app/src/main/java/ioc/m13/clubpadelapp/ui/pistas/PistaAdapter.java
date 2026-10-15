package ioc.m13.clubpadelapp.ui.pistas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ioc.m13.clubpadelapp.R;
import ioc.m13.clubpadelapp.data.model.Pista;

public class PistaAdapter extends RecyclerView.Adapter<PistaAdapter.VH> {

    public interface OnPistaClick {
        void onClick(Pista p);
    }

    private List<Pista> pistas;
    private final OnPistaClick listener;

    public PistaAdapter(List<Pista> pistas, OnPistaClick listener) {
        this.pistas = pistas;
        this.listener = listener;
    }

    public void setPistas(List<Pista> nuevas) {
        this.pistas = nuevas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pista, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        Pista p = pistas.get(pos);
        h.tvNombre.setText(p.getNombre());
        h.tvTipo.setText(p.getTipo());

        // Estado con indicador de color
        String estado = p.getEstado();
        h.tvEstado.setText("● " + estado);
        if ("Disponible".equals(estado)) {
            h.tvEstado.setTextColor(0xFF5DD37F); // verde
            h.btnReservar.setEnabled(true);
            h.btnReservar.setAlpha(1f);
        } else {
            h.tvEstado.setTextColor(0xFFFFB020); // naranja
            h.btnReservar.setEnabled(false);
            h.btnReservar.setAlpha(0.5f);
        }

        // Click en la tarjeta entera
        h.itemView.setOnClickListener(v -> listener.onClick(p));

        // Click en el botón "Reservar"
        h.btnReservar.setOnClickListener(v -> listener.onClick(p));
    }

    @Override
    public int getItemCount() {
        return pistas.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvTipo, tvEstado;
        Button btnReservar;

        VH(@NonNull View v) {
            super(v);
            tvNombre = v.findViewById(R.id.tvNombrePista);
            tvTipo = v.findViewById(R.id.tvTipoPista);
            tvEstado = v.findViewById(R.id.tvEstadoPista);
            btnReservar = v.findViewById(R.id.btnReservarPista);
        }
    }
}
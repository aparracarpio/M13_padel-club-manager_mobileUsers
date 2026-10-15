package ioc.m13.clubpadelapp.ui.reservas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ioc.m13.clubpadelapp.R;
import ioc.m13.clubpadelapp.data.model.ReservaResponse;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.VH> {

    public interface OnCancelar { void onClick(Long id); }

    private List<ReservaResponse> reservas;
    private final OnCancelar listener;

    public ReservaAdapter(List<ReservaResponse> reservas, OnCancelar listener) {
        this.reservas = reservas;
        this.listener = listener;
    }

    public void setReservas(List<ReservaResponse> nuevas) {
        this.reservas = nuevas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reserva, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        ReservaResponse r = reservas.get(pos);
        h.tvPista.setText(r.getNombrePista());
        h.tvFecha.setText(r.getFecha());
        h.tvHoras.setText(r.getHoraInicio() + " - " + r.getHoraFin());
        h.tvEstado.setText(r.getEstado());

        if ("Activa".equals(r.getEstado())) {
            h.btnCancelar.setVisibility(View.VISIBLE);
            h.btnCancelar.setOnClickListener(v -> listener.onClick(r.getId()));
        } else {
            h.btnCancelar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() { return reservas.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvPista, tvFecha, tvHoras, tvEstado;
        Button btnCancelar;
        VH(@NonNull View v) {
            super(v);
            tvPista = v.findViewById(R.id.tvPistaReserva);
            tvFecha = v.findViewById(R.id.tvFechaReserva);
            tvHoras = v.findViewById(R.id.tvHorasReserva);
            tvEstado = v.findViewById(R.id.tvEstadoReserva);
            btnCancelar = v.findViewById(R.id.btnCancelarReserva);
        }
    }
}
<?php

include_once 'db_abstract_model.php';

class Ticket_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'ticket';
        }
       
	   function get_by_id($id){
                $this->db->where('id_ticket', $id);
				$this->db->where('tipo !=',2);
                return $this->db->get($this->tbl)->row_array();
       }
	   
	   function get_family_ticket($user_id){
			$sql = "SELECT t.* FROM cliente_genera_tickets ct , ticket t  WHERE ct.id_ticket=t.id_ticket and t.tipo=2 and ct.id_cliente =".$user_id;
	   		error_log($sql);
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->row_array();
			}
			return null;
	   }
	   
	   function get_tickets_by_client_device($user_id,$imei){
			$sql = "SELECT t.id_ticket,t.descripcion FROM  ticket t, disp_reporta_ticket dt  WHERE t.id_ticket = dt.id_ticket AND t.estado > 0 AND t.tipo = 1 AND dt.id_dispositivo = ".$imei." ORDER BY id_ticket asc";
			error_log($sql);
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->result_array();
			}
			return null;
	   }
	   
	   function set_ticket_entregado($user_id,$imei,$ticket_id){
			$sql = "SELECT t.id_ticket,t.descripcion FROM  ticket t, disp_reporta_ticket dt  WHERE  t.id_ticket = dt.id_ticket AND t.estado > 0 AND t.tipo = 1  AND dt.id_dispositivo = ".$imei." AND dt.id_ticket = ".$ticket_id;
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				$sql = "UPDATE  ticket SET  estado='0' WHERE  id_ticket =".$ticket_id;
				$this->db->query($sql);
				error_log("marcado ticket: ".$ticket_id);
				return true;
			}
			return false;
	   }
	   
  		function save_ticket($data,$imei){
  				$id = $this->save($data);
				if($id){
					  $reportero = array("id_ticket"=>$id,"id_dispositivo"=>$imei);
					  $this->db->insert("disp_reporta_ticket", $reportero);
					return  $id;
				}else{		
					return -1;
				}
        }
        
        function get_listado_empanadas(){
        	$sql = "SELECT t.id_ticket, t.descripcion, d.descripcion  datos FROM  ticket t, disp_reporta_ticket dt, dispositivo d  WHERE  t.estado > 0 AND t.tipo = 1 AND t.id_ticket=dt.id_ticket AND dt.id_dispositivo=d.imei ORDER BY id_ticket asc";
			error_log($sql);
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->result_array();
			}
			return null;        	
        }
        	
}
?>
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Tecnico } from '../../models/tecnico';

@Component({
  selector: 'app-tecnicos',
  standalone: false,
  templateUrl: './tecnicos.component.html',
  styleUrls: ['./tecnicos.component.css'],
})
export class TecnicosComponent implements OnInit{
  ELEMENT_DATA: Tecnico[]=[
    {
      id: 1,
      name: 'Tecnico 1',
      cpf: '123.456.789-00',
      email: 'douglas.empresa@email.com',
      senha: '123456',
      perfis: ['0'],
      dataCriacao: '2023-10-01',
    }
  ]
  displayedColumns: string[] = ['id', 'name', 'cpf', 'email', 'acoes'];
  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  constructor() {}
  ngOnInit(): void {
      
  }
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
}


export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}


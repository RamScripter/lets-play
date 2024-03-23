import { Component, OnInit, Input } from '@angular/core';
import { Ad } from '../models/ad.model';
import { AdService } from '../services/ad.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-single-ad',
  templateUrl: './single-ad.component.html',
  styleUrls: ['./single-ad.component.css']
})

export class SingleAdComponent implements OnInit{
  ad!: Ad;

  constructor(private route: ActivatedRoute, private adService: AdService) {}

  id!: number;
  createdAt!: Date;
  title!: string;
  userType!: string;
  seeking!: string;
  image: string | undefined;
  style!: string;
  description!: string;


  ngOnInit(): void {
    const adId = this.route.snapshot.params['id'];
    this.ad = this.adService.getAdById(+adId);
  }


}

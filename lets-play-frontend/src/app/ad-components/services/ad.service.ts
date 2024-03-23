import { Injectable } from '@angular/core';
import { Ad } from '../models/ad.model';

@Injectable({
    providedIn: 'root'
})

export class AdService {
    ads: Ad[] = [
        {
            id: 1,
            createdAt: new Date(),
            title: "Metal band",
            userType: "band", 
            seeking: "guitar player",
            image: "../assets/images/metal-band.jpg",
            style: "Death metal",
            description: "We're a brand new band"
          },
          {
            id: 2,
            createdAt: new Date(),
            title: "Michael, guitar player",
            userType: "musician",
            seeking: "band",
            image: "../assets/images/guitar.jpg",
            style: "Death metal",
            description: "Looking for my first band experience"
          },
          {
            id: 3,
            createdAt: new Date(),
            title: "Another Metal band",
            userType: "band",
            seeking: "drummer",
            image: "../assets/images/metal-band.jpg",
            style: "Death metal",
            description: "We're a brand new band"
          },
          {
            id: 4,
            createdAt: new Date(),
            title: "Drummer",
            userType: "musician",
            seeking: "band",
            image: "../assets/images/drums.jpg",
            style: "Death metal",
            description: "11 years of experience"
          }
    ];

    getAllAds(): Ad[] {
        return this.ads;
    }
    getAdById(id: number): Ad {
      const ad = this.ads.find(ad => ad.id === id);
      if (!ad) {
        throw new Error('Ad not found');  
      } else {
        return ad;
      }
    }

    // // création d'un type littéral "snapType" qui ne peut correspondre qu'à "snap" ou "unsnap"
    // updateSnapsNumber(faceSnapId: number, snapType: "snap" | "unsnap"): void{
    //     const faceSnap = this.getSnapFaceById(faceSnapId);
    //     if (!faceSnap) { 
    //         throw new Error('FaceSnap not found');  
    //     } else {
    //         snapType === "snap" ? faceSnap.snaps++ : faceSnap.snaps--;
    //     }
    // }
}

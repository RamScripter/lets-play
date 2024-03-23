import { Injectable } from '@angular/core';
import { CloudinaryConfig } from '@cloudinary/url-gen';

@Injectable({
  providedIn: 'root'
})
export class CloudinaryService {
  
  private cloudinaryConfig: CloudinaryConfig;

  constructor() {
    this.cloudinaryConfig = new CloudinaryConfig({
      cloud: {
        cloudName: 'LetsPlay',
        apiKey: '397389777628642',
        apiSecret: 'y2712Vwa-M56Cd_nOyR3SyJDNM8'
      }
    });
  }

  getCloudinaryConfig(): CloudinaryConfig {
    return this.cloudinaryConfig;
  }
}

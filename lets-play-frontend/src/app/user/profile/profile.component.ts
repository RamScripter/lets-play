import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/authentication/services/authentication.service';
import { User } from '../models/user.model';

// Import the CloudinaryModule.
import { CloudinaryService } from 'src/app/cloudinary/cloudinary.service';


// Import the Cloudinary classes.
import {Cloudinary, CloudinaryImage, CloudinaryConfig} from '@cloudinary/url-gen';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  user!: User | null;
  public newPassword: String = '';
  public confirmNewPassword: String = '';
  img!: CloudinaryImage;
  myWidget: any;
  uploadPreset = "ml_default"; // replace with your own upload preset


  constructor(private authService: AuthenticationService, private cloudinaryService: CloudinaryService,
    private cloudinary: Cloudinary) {}

  ngOnInit() {
    this.authService.getCurrentUser().subscribe((user: User | null) => {
      this.user = user;
    });

    const cloudinaryConfig: CloudinaryConfig = this.cloudinaryService.getCloudinaryConfig();

    
    const cld = new Cloudinary(cloudinaryConfig);

    // cld.image returns a CloudinaryImage with the configuration set.
    this.img = cld.image('sample');

    // @ts-ignore: Unreachable code error
    this.myWidget = cloudinary.createUploadWidget(
      {
        cloud: cld,
        uploadPreset: this.uploadPreset,
        // cropping: true, //add a cropping step
        // showAdvancedOptions: true,  //add advanced options (public_id and tag)
        sources: [ "local", "url"], // restrict the upload sources to URL and local files
        // multiple: false,  //restrict upload to a single file
        // folder: "user_images", //upload files to the specified folder
        tags: ["users", "profile"], //add the given tags to the uploaded files
        // context: {alt: "user_uploaded"}, //add the given context data to the uploaded files
        clientAllowedFormats: ["images"], //restrict uploading to image files only
        maxImageFileSize: 2000000,  //restrict file size to less than 2MB
        // maxImageWidth: 2000, //Scales the image down to a width of 2000 pixels before uploading
        // theme: "purple", //change to a purple theme
      },
        // @ts-ignore: Unreachable code error
      (error, result) => {
        if (!error && result && result.event === "success") {
          console.log("Done! Here is the image info: ", result.info);
          // @ts-ignore: Unreachable code error
          document
            .getElementById("uploadedimage")
            .setAttribute("src", result.info.secure_url);
        }
      }
    );
  }

  openWidget() {

  }

  updateProfile() {
    if (this.newPassword === this.confirmNewPassword && this.user) {
      this.authService.updateUser(this.newPassword, this.user.id).subscribe(() => {
        this.newPassword = '';
        this.confirmNewPassword = '';
      });
    }
  }

  updateProfilePicture(event: any) {
    
    // const file = event.target.files[0];
    // this.authService.updateProfilePicture(file).subscribe(() => {
    //   this.ngOnInit();
    // });
  }
  

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class Arcade3 extends LinearOpMode{
    
    private DcMotor frontleft;
    private DcMotor backleft;
    private DcMotor frontright;
    private DcMotor backright;
    private DcMotor anActualGun;
    private DcMotor literallyAGun;
    private DcMotor slurp;
    private DcMotor fwip;
    private Servo flicky;
  private Servo theClaaaw;


@Override
public void runOpMode(){
    frontleft = hardwareMap.get(DcMotor.class,"frontleft");
    backleft = hardwareMap.get(DcMotor.class,"backleft");
    frontright = hardwareMap.get(DcMotor.class,"frontright");
    backright = hardwareMap.get(DcMotor.class,"backright");
    anActualGun = hardwareMap.get(DcMotor.class,"anActualGun");
    literallyAGun = hardwareMap.get(DcMotor.class,"literallyAGun");
    flicky = hardwareMap.get(Servo.class,"flicky"); 
    slurp = hardwareMap.get(DcMotor.class,"slurp");
    fwip = hardwareMap.get(DcMotor.class,"fwip");
    theClaaaw = hardwareMap.get(Servo.class,"theClaaaw");
    
    double throttle = 0;
    double direction = 0;
    double throttle2 = 0;
    double direction2 = 0;
    frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
    backleft.setDirection(DcMotorSimple.Direction.REVERSE);
    slurp.setDirection(DcMotorSimple.Direction.REVERSE);
  //  cube.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    
    
    telemetry.addData("Status","Initialized");
    telemetry.update();
    waitForStart();
    
    while (opModeIsActive()) {
        //int cposition = cube.getCurrentPosition();
      //  telemetry.addData("Arm", cposition);
        
        throttle = -this.gamepad1.left_stick_y;
        direction = this.gamepad1.left_stick_x/2;
        throttle2 = this.gamepad1.right_stick_y/2;
        direction2 = this.gamepad1.right_stick_x/4;
        
        double right2 = throttle2 - direction2;
        double left2 = throttle2 + direction2;
        double right = throttle - direction;
        double left = throttle + direction;
        // clip the right/left values so that the values never exceed +/- 1
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);
        right2 = Range.clip(right2, -1, 1);
        left2 = Range.clip(left2, -1, 1);

        // write the values to the motors
       
        if(gamepad1.right_stick_y>0 || gamepad1.right_stick_y<0 ||
        gamepad1.right_stick_x>0 || gamepad1.right_stick_x<0){
            frontright.setPower(right2);
            frontleft.setPower(left2);
            backright.setPower(right2);
            backleft.setPower(left2);
        } else{      
            frontright.setPower(right);
            frontleft.setPower(left);
            backright.setPower(right);
            backleft.setPower(left);
        }
        
        
        
        if(gamepad1.y){
            anActualGun.setPower(.9);
            literallyAGun.setPower(.9);
        }else if(gamepad1.a){
            anActualGun.setPower(0);
            literallyAGun.setPower(0);
        }
        
        if (gamepad1.right_bumper){
           slurp.setPower(1);
       // }else if(gamepad1.dpad_down){
       //     slurp.setPower(-1);
        }else{
            slurp.setPower(0);
        }
        
        if(gamepad1.right_trigger>0){
            flicky.setPosition(.55); 
        }else{
            flicky.setPosition(.71);
        }
        
        if(gamepad1.x){
            fwip.setPower(.75);
        }else if(gamepad1.b){
            fwip.setPower(-.75);
        }else{
            fwip.setPower(0);
        }
        
        if(gamepad1.left_trigger>0){
            theClaaaw.setPosition(.69);
        }else if(gamepad1.left_bumper){
          theClaaaw.setPosition(0);
        }
        
   //     if(gamepad1.dpad_up){
    //        anActualGun.setPower(1);
    //    literallyAGun.setPower(1);
        
        
    //    sleep(750);
    //    flicky.setPosition(.6);
    //    sleep(1500);
     //   flicky.setPosition(.73);
        
      //  frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); 
        
      //  frontright.setTargetPosition(-30);
      //  frontleft.setTargetPosition(30);
      //  backright.setTargetPosition(-30);
      //  backleft.setTargetPosition(30);
        
       // frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // backright.setMode(DcMotor.RunMode.RUN_TO_POSITION); 
        
       // frontright.setPower(.5);
       // frontleft.setPower(.5);
       // backright.setPower(.5);
       // backleft.setPower(.5);
       // sleep(1000);
       // flicky.setPosition(.6);
       // sleep(1000);
        
       // flicky.setPosition(.73);
       // frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); 
        
       // frontright.setTargetPosition(-40);
       // frontleft.setTargetPosition(40);
       // backright.setTargetPosition(-40);
       // backleft.setTargetPosition(40);
        
       // frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // backright.setMode(DcMotor.RunMode.RUN_TO_POSITION); 
        
       // frontright.setPower(.5);
       // frontleft.setPower(.5);
       // backright.setPower(.5);
       // backleft.setPower(.5);
       // sleep(1000);
        
       // flicky.setPosition(.6);
       // sleep(1000);
       // anActualGun.setPower(0);
       // literallyAGun.setPower(0);
       // flicky.setPosition(.73);
       // frontright.setPower(0);
       // frontleft.setPower(0);
       // backright.setPower(0);
       // backleft.setPower(0);
       // frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
       // frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
       // backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
       // backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
       // fwip.setPower(-.4);
       // sleep(1500);
       // }

        telemetry.addData("Status","Running");
        telemetry.update();
    }
}
}
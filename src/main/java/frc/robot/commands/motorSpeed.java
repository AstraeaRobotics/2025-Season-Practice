// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.TankDrivebase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;

public class motorSpeed extends Command {
  /** Creates a new motorSpeed. */
   private final double m_motorSpeed;
  private final TankDrivebase m_motor1;
  private final TankDrivebase m_motor2;
  private final TankDrivebase m_motor3;
  private final TankDrivebase m_motor4;

  public motorSpeed (TankDrivebase motor1,TankDrivebase motor2,TankDrivebase motor3,TankDrivebase motor4, double motorSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_motor1 = motor1;
    m_motor2 = motor2;
    m_motor3 = motor3;
    m_motor4 = motor4;
  
    m_motorSpeed = motorSpeed;
    addRequirements();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
    public void curve(double speed,double turn, boolean turnInPlace){
    WheelSpeeds speeds = DifferentialDrive.curvatureDriveIK(speed,turn,turnInPlace);
  }

  @Override

  public void execute() {
    m_motor1.setSpeed(speeds.left);
    m_motor2.setSpeed(speeds.left);
    m_motor3.setSpeed(speeds.right);
    m_motor4.setSpeed(speeds.right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_motor1.setSpeed(0);
    m_motor2.setSpeed(0);
    m_motor3.setSpeed(0);
    m_motor4.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import org.opencv.video.TrackerNano;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveSub;

public class Drive extends Command {
  /** Creates a new TankDrive. */
  private TankDriveSub m_tanksub;
  private DoubleSupplier m_speedSupplier;
  private DoubleSupplier m_turnSupplier;

  public Drive(TankDriveSub driveBase, DoubleSupplier frSpeed, DoubleSupplier turn) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_tanksub = driveBase;
    m_speedSupplier = frSpeed;
    m_turnSupplier = turn;

    addRequirements(m_tanksub);
  }

 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = m_speedSupplier.getAsDouble()*.15;
    double turn = m_turnSupplier.getAsDouble();

    if(Math.abs(speed) < .1){ speed = 0;}
    if(Math.abs(turn) < .1){ turn = 0;}
    
    m_tanksub.CurveDrive(speed, turn , true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

